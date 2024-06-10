package ppj.assignments.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ppj.assignments.data.City;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {
    City findByName(String name);
    boolean existsByName(String name);
    void deleteByName(String name);

}
