package app.business.model;

import app.business.enums.Face;

public class SensorEvent {
    private Long id;
    private Face face;
    private double temperature;
    private long timestamp;

    public SensorEvent() {
    }

    public SensorEvent(Long id, Face face, double temperature, long timestamp) {
        this.id = id;
        this.face = face;
        this.temperature = temperature;
        this.timestamp = timestamp;
    }

    public SensorEvent(Long id, Face face, double temperature) {
        this.id = id;
        this.face = face;
        this.temperature = temperature;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Face getFace() {
        return face;
    }

    public void setFace(Face face) {
        this.face = face;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "SensorEvent{" +
                "id=" + id +
                ", face=" + face +
                ", temperature=" + temperature +
                ", timestamp=" + timestamp +
                '}';
    }
}
