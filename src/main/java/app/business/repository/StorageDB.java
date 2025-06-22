package app.business.repository;

import app.business.model.SensorEvent;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class StorageDB {

    private final List<SensorEvent> storage = new CopyOnWriteArrayList<>();

    public void save(SensorEvent event) {
        storage.add(event);
    }

    public List<SensorEvent> getAll() {
        return storage;
    }
}
