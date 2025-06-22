package app.business.service.registry;

import app.business.enums.Face;
import java.util.Map;

public interface SensorRegistryService {

    Long registerSensor(Face face);
    void removeSensor(Long id);
    Map<Long, Face> getAllSensors();
}
