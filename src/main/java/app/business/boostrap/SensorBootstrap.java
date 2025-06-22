package app.business.boostrap;

import app.business.enums.Face;
import app.business.service.registry.SensorRegistryService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
public class SensorBootstrap implements CommandLineRunner {

    private final SensorRegistryService registry;

    public SensorBootstrap(SensorRegistryService registry) {
        this.registry = registry;
    }

    @Override
    public void run(String... args) {
        System.out.println("🚀 Initializing sensors...");
        for (Face face : Face.values()) {
            for (int i = 0; i < 3; i++) {
                registry.registerSensor(face);
            }
        }
        System.out.println("✅ Done: " + registry.getAllSensors().size() + " sensors registered.");
    }
}