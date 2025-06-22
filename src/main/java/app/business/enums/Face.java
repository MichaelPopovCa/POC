package app.business.enums;

import java.util.Random;

public enum Face {

    north, south, east, west;

    public static Face randomFace() {
        return Face.values()[new Random().nextInt(Face.values().length)];
    }
}
