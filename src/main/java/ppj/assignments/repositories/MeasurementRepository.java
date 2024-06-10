package ppj.assignments.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ppj.assignments.data.City;
import ppj.assignments.data.Measurement;

import java.util.List;


@Repository
public interface MeasurementRepository extends JpaRepository<Measurement, Long> {

        List<Measurement> findByCity(City city);

        List<Measurement> findByCityName(@Param("cityName") String cityName);

}
