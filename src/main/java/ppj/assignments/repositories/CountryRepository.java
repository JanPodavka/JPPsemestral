package ppj.assignments.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ppj.assignments.data.Country;

@Repository
public interface CountryRepository extends JpaRepository<Country, String> {

        boolean existsByCountryCode(String CountryCode);
        Country findByCountryCode(String countryCode);

}
