package app.business.event;

import app.business.enums.Face;
import app.business.messagebus.MessageBus;
import app.business.model.SensorEvent;
import app.business.service.registry.SensorRegistryService;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Map;
import java.util.Random;

@Component
@Order(3)
public class SensorEventGenerator  {
    private final SensorRegistryService registry;
    private final MessageBus kafka;

    public SensorEventGenerator(SensorRegistryService registry, MessageBus kafka) {
        this.registry = registry;
        this.kafka = kafka;
    }

    @Scheduled(fixedRate = 1000)
    public void generate() {
        if(registry.getAllSensors().isEmpty()) {
            System.out.println("The list sensors is empty");
            return;
        }
        for (Map.Entry<Long, Face> entry : registry.getAllSensors().entrySet()) {
            SensorEvent event = new SensorEvent(
                    entry.getKey(),
                    entry.getValue(),
                    20.0 + new Random().nextDouble() * 15,
                    Instant.now().getEpochSecond()
            );
            kafka.send(event);
            System.out.println("✅ Sent: " + event);
        }
    }
}
