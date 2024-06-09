package ppj.assignments.data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "country")
public class Country {
    @Id
    private String countryCode; //countrycode



    public Country() {}
    public Country (String countryCode) {
        this.countryCode = countryCode;
    }


    public String getCountryCode() {
        return countryCode;
    }


    public void setCountryCode(String id) {
        this.countryCode = id;
    }


}
