package app.business.model;

public class MalfunctioningSensorReport {

    private final long id;
    private final double averageTemperature;

    public MalfunctioningSensorReport(long id, double averageTemperature) {
        this.id = id;
        this.averageTemperature = averageTemperature;
    }

    public long getId() {
        return id;
    }

    public double getAverageTemperature() {
        return averageTemperature;
    }
}
