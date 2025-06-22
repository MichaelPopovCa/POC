package app.business.model;

import app.business.enums.Face;

public class SensorDTO {

    private Face face;

    public SensorDTO() {
    }

    public SensorDTO(Face face, double temperature) {
        this.face = face;
    }

    public Face getFace() {
        return face;
    }

    public void setFace(Face face) {
        this.face = face;
    }

    @Override
    public String toString() {
        return "SensorDTO{" +
                ", face=" + face +
                '}';
    }
}
