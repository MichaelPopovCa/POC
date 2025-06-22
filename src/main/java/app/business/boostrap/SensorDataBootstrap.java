package app.business.boostrap;

import app.business.enums.Face;
import app.business.messagebus.MessageBus;
import app.business.model.SensorEvent;
import app.business.service.registry.SensorRegistryService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import java.time.Instant;
import java.util.Map;
import java.util.Random;

@Component
@Order(2)
public class SensorDataBootstrap implements CommandLineRunner {

    private final SensorRegistryService registry;
    private final MessageBus kafka;

    public SensorDataBootstrap(SensorRegistryService registry, MessageBus kafka) {
        this.registry = registry;
        this.kafka = kafka;
    }

    @Override
    public void run(String... args) {
        System.out.println("🚀 Bootstrap: Initializing sensors...");

        System.out.println("✅ Sensors initialized. Generating 7 days of data...");

        long minutesBack = 7L * 24L * 60L;
        Instant now = Instant.now();
        int count = 0;

        for (Map.Entry<Long, Face> entry : registry.getAllSensors().entrySet()) {
            Long id = entry.getKey();
            Face face = entry.getValue();
            boolean isMalfunctioning = (count++ % 10 == 0); // каждые 10-й

            for (int i = 0; i < minutesBack; i++) {
                Instant timestamp = now.minusSeconds(60L * i);
                double temperature = isMalfunctioning
                        ? 60.0 + new Random().nextDouble() * 10000
                        : 20.0 + new Random().nextDouble() * 15;

                SensorEvent event = new SensorEvent(
                        id,
                        face,
                        temperature,
                        timestamp.getEpochSecond()
                );
                kafka.send(event);
            }

            System.out.printf("✅ Sensor %d [%s] → %s%n", id, face,
                    (isMalfunctioning ? "⚠️ MALFUNCTIONING" : "OK"));
        }

        System.out.println("✅ All historical data generated for 7 days.");
    }
}
