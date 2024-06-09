package ppj.assignments.data;

import javax.persistence.*;

@Entity
@Table(name = "city")
public class City {
    @Id
    private Long id; //cityID
    private String name;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;



    //Definice konstruktorů a getterů
    public City() {
        this.country = new Country();
    }
    public City(Long id, String name, Country country) {
        this.id = id;
        this.name = name;
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public Country getCountry() {
        return country;
    }


    public Long getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}
