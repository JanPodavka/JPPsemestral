package ppj.assignments.data;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "measurement")
public class Measurement {

    @Id
    private long id;
    private Timestamp timestamp;
    private double temperature;
    private int humidity;
    private double windSpeed;

    @Id
    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;


}
