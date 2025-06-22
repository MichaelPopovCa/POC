package app.business.service.registry;

import app.business.enums.Face;
import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class SensorRegistryServiceImpl implements SensorRegistryService{

    public SensorRegistryServiceImpl() {
        System.out.println("🧠 SensorRegistryServiceImpl initialized: " + this);
    }

    private final Map<Long, Face> sensors = new ConcurrentHashMap<>();
    private final AtomicLong idSequence = new AtomicLong(1000);

    public Long registerSensor(Face face) {
        Long id = idSequence.incrementAndGet();
        sensors.put(id, face);
        System.out.println("✅ [SensorRegistryServiceImpl] Added sensor: " + id + ", face=" + face);
        return id;
    }

    public void removeSensor(Long id) {
        sensors.remove(id);
    }

    public Map<Long, Face> getAllSensors() {
        return sensors;
    }
}
