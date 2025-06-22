package app.business.model;

import app.business.enums.Face;

public class SensorRedis {

    private long id;
    private Face face;

    public SensorRedis() {
    }

    public SensorRedis(long id, Face face) {
        this.id = id;
        this.face = face;
    }

    public long getId() {
        return id;
    }

    public Face getFace() {
        return face;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setFace(Face face) {
        this.face = face;
    }
}
