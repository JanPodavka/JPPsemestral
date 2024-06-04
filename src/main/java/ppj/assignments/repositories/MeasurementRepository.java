package ppj.assignments.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ppj.assignments.data.Measurement;


@Repository
public interface MeasurementRepository extends CrudRepository<Measurement, Long> {

}
