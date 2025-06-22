package app.business.model;

import java.time.Instant;

public class HourlyAvgReport {

    private final Instant hour;
    private final double averageTemperature;

    public HourlyAvgReport(Instant hour, double averageTemperature) {
        this.hour = hour;
        this.averageTemperature = averageTemperature;
    }

    public Instant getHour() {
        return hour;
    }

    public double getAverageTemperature() {
        return averageTemperature;
    }
}
