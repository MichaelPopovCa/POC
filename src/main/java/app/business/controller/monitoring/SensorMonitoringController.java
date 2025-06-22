package app.business.controller.monitoring;

import app.business.model.HourlyAvgReport;
import app.business.model.MalfunctioningSensorReport;
import app.business.service.monitoring.SensorMonitoringService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("monitoring")
public class SensorMonitoringController {

    private final SensorMonitoringService sensorMonitoringService;

    public SensorMonitoringController(SensorMonitoringService sensorMonitoringService) {
        this.sensorMonitoringService = sensorMonitoringService;
    }

    @GetMapping("/report/hourly-avg")
    public List<HourlyAvgReport> reportHourlyAverage() {
        return sensorMonitoringService.reportHourlyAverage();
    }

    @GetMapping("/report/malfunctioning")
    public List<MalfunctioningSensorReport> reportMalfunctioningSensors() {
        return sensorMonitoringService.reportMalfunctioningSensors();
    }
}
