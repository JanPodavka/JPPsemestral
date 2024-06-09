package ppj.assignments.data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.TimeZone;


@Entity
@Table(name = "country")
public class Country {
    @Id
    private Long id; //countrycode
    private String name;
    private TimeZone timeZone;


    public Country() {}
    public Country (Long id, String name, TimeZone timeZone) {
        this.id = id;
        this.name = name;
        this.timeZone = timeZone;
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }

    public TimeZone getTimeZone() {
        return timeZone;
    }
    public void setTimeZone(TimeZone timeZone) {}

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
