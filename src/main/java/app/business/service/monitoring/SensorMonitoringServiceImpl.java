package app.business.service.monitoring;

import app.business.enums.Face;
import app.business.model.HourlyAvgReport;
import app.business.model.MalfunctioningSensorReport;
import app.business.model.SensorEvent;
import app.business.repository.StorageDB;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class SensorMonitoringServiceImpl implements SensorMonitoringService {

    private final StorageDB inMemoryInfluxDB;

    public SensorMonitoringServiceImpl(StorageDB inMemoryInfluxDB) {
        this.inMemoryInfluxDB = inMemoryInfluxDB;
    }

    @Override
    public List<HourlyAvgReport> reportHourlyAverage() {
        Instant oneWeekAgo = Instant.now().minus(7, ChronoUnit.DAYS);
        Map<Instant, List<SensorEvent>> hourlyBuckets = new TreeMap<>();

        System.out.println("📊 Starting hourly average aggregation for the past week...");

        for (SensorEvent event : inMemoryInfluxDB.getAll()) {
            if (event.getTimestamp() >= oneWeekAgo.getEpochSecond()) {
                Instant hourKey = Instant.ofEpochSecond(event.getTimestamp())
                        .truncatedTo(ChronoUnit.HOURS);
                hourlyBuckets
                        .computeIfAbsent(hourKey, k -> new ArrayList<>())
                        .add(event);
            }
        }

        List<HourlyAvgReport> reports = hourlyBuckets.entrySet().stream()
                .map(entry -> {
                    double avg = entry.getValue().stream()
                            .mapToDouble(SensorEvent::getTemperature)
                            .average()
                            .orElse(0);
                    System.out.printf("⏱️ Hour: %s → Avg Temp: %.2f°C (%d events)%n", entry.getKey(), avg, entry.getValue().size());
                    return new HourlyAvgReport(entry.getKey(), avg);
                })
                .collect(Collectors.toList());

        System.out.printf("✅ Hourly report ready: %d buckets%n", reports.size());
        return reports;
    }

    @Override
    public List<MalfunctioningSensorReport> reportMalfunctioningSensors() {
        System.out.println("🚨 Checking for malfunctioning sensors...");

        Map<Long, List<SensorEvent>> grouped = inMemoryInfluxDB.getAll().stream()
                .collect(Collectors.groupingBy(SensorEvent::getId));

        Map<Long, Double> avgBySensor = grouped.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        e -> e.getValue().stream().mapToDouble(SensorEvent::getTemperature).average().orElse(0)
                ));

        Map<Long, Face> faceBySensor = grouped.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        e -> e.getValue().get(0).getFace()
                ));

        List<MalfunctioningSensorReport> result = avgBySensor.entrySet().stream()
                .filter(entry -> {
                    Long id = entry.getKey();
                    Face face = faceBySensor.get(id);
                    double sensorAvg = entry.getValue();

                    List<Double> peerAverages = avgBySensor.entrySet().stream()
                            .filter(e -> !e.getKey().equals(id) && faceBySensor.get(e.getKey()) == face)
                            .map(Map.Entry::getValue)
                            .toList();

                    if (peerAverages.isEmpty()) return false;

                    double peerAvg = peerAverages.stream().mapToDouble(Double::doubleValue).average().orElse(0);
                    double deviation = Math.abs(sensorAvg - peerAvg) / peerAvg;

                    System.out.printf("🔍 Sensor %d [%s] → SensorAvg: %.2f, PeerAvg: %.2f, Deviation: %.2f%%%n",
                            id, face, sensorAvg, peerAvg, deviation * 100);

                    return deviation > 0.2;
                })
                .map(entry -> new MalfunctioningSensorReport(entry.getKey(), entry.getValue()))
                .toList();

        System.out.printf("⚠️ Found %d malfunctioning sensors%n", result.size());
        return result;
    }
}
