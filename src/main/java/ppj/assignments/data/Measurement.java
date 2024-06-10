package ppj.assignments.data;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "measurement")
public class Measurement implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;
    private Timestamp timestamp;
    private double temperature;
    private int humidity;
    private double windSpeed;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

    //Konstruktory a gettery

    public Measurement() {}
    public Measurement(final long id, final Timestamp timestamp, final double temperature, final int humidity,final double windSpeed, final City city) {
        this.id = id;
        this.timestamp = timestamp;
        this.temperature = temperature;
        this.humidity = humidity;
        this.windSpeed = windSpeed;
        this.city = city;
    }


    public City getCity() {
        return city;
    }

    public long getId() {
        return id;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public double getTemperature() {
        return temperature;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public int getHumidity() {
        return humidity;
    }
    public void setId(final long id) {
        this.id = id;
    }
    public void setTimestamp(final Timestamp timestamp) {
        this.timestamp = timestamp;
    }
    public void setTemperature(final double temperature) {
        this.temperature = temperature;
    }
    public void setWindSpeed(final double windSpeed) {
        this.windSpeed = windSpeed;
    }
    public void setHumidity(final int humidity) {
        this.humidity = humidity;
    }
    public void setCity(final City city) {
        this.city = city;
    }
}
