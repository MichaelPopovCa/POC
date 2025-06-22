package app.business.consumer;

import app.business.messagebus.MessageBus;
import app.business.repository.StorageDB;
import org.springframework.stereotype.Component;

@Component
public class SensorEventConsumer {

    public SensorEventConsumer(MessageBus kafka, StorageDB db) {
        kafka.subscribe(event -> {
            db.save(event);
            System.out.printf("📥 Received: id=%d, face=%s, temp=%.2f, ts=%d%n",
                    event.getId(), event.getFace(), event.getTemperature(), event.getTimestamp());
        });
    }
}
