package ppj.assignments.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ppj.assignments.data.City;

@Repository
public interface CityRepository extends CrudRepository<City, Long> {

}
