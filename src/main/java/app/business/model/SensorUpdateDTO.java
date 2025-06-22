package app.business.model;

public class SensorUpdateDTO {

    private int id;

    public SensorUpdateDTO() {
    }

    public SensorUpdateDTO(int id) {
        this.id = id;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "SensorDTO{" +
                ", id=" + id +
                '}';
    }
}
