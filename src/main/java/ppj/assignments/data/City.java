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



    //Definice konstruktorů a getterů
    public City() {
        this.country = new Country();
    }
    public City(Long id, String name, int population, Country country) {
        this.id = id;
        this.name = name;
        this.population = population;
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public Country getCountry() {
        return country;
    }

    public int getPopulation() {
        return population;
    }

    public Long getId() {
        return id;
    }
}
