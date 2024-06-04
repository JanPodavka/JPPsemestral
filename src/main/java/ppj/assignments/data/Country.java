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

}
