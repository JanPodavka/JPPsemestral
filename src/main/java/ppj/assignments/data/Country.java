package ppj.assignments.data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "country")
public class Country {
    @Id
    private String countryCode; //countrycode
    private float latitude;
    private float longitude;


    public Country() {}
    public Country (String countryCode, float latitude, float longitude) {
        this.countryCode = countryCode;
        this.latitude = latitude;
        this.longitude = longitude;
    }


    public String getCountryCode() {
        return countryCode;
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


    public void setCountryCode(String id) {
        this.countryCode = id;
    }


}
