package app.business.messagebus;

import app.business.model.SensorEvent;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

@Component
public class MessageBus {

    private final List<Consumer<SensorEvent>> listeners = new ArrayList<>();

    public void send(SensorEvent event) {
        for (var listener : listeners) {
            listener.accept(event);
        }
    }

    public void subscribe(Consumer<SensorEvent> consumer) {
        listeners.add(consumer);
    }
}
