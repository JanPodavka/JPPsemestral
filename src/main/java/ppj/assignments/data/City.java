package ppj.assignments.data;

import javax.persistence.*;

@Entity
@Table(name = "city")
public class City {

    @Id
    private String name;
    private float latitude;
    private float longitude;
    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;



    //Definice konstruktorů a getterů
    public City() {
    }

    public City(String name, Country country, float latitude, float longitude) {
        this.name = name;
        this.country = country;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getName() {
        return name;
    }

    public Country getCountry() {
        return country;
    }




    public void setName(String name) {
        this.name = name;
    }


    public void setCountry(Country country) {
        this.country = country;
    }

    public float getLatitude() {
        return latitude;
    }
    public float getLongitude() {
        return longitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }
}
