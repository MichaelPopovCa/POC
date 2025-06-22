package app.business.service.monitoring;

import app.business.model.HourlyAvgReport;
import app.business.model.MalfunctioningSensorReport;
import java.util.List;

public interface SensorMonitoringService {

    List<HourlyAvgReport> reportHourlyAverage();
    List<MalfunctioningSensorReport> reportMalfunctioningSensors();
}
