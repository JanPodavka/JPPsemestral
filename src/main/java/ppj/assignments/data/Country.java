package ppj.assignments.data;

import javax.persistence.*;
import java.util.TimeZone;


@Entity
@Table(name = "country")
public class Country {
    @Id
    private Long id; //countrycode
    private String name;
    private TimeZone timeZone;

}
