package ppj.assignments.data;

import javax.persistence.*;

@Entity
@Table(name = "city")
public class City {
    @Id
    private Long id; //cityID
    private String name;
    private int population;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;

}
