package ppj.assignments.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ppj.assignments.data.Country;

import java.util.List;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {



}
