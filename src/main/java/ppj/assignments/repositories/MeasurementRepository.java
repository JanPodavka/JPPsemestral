package ppj.assignments.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ppj.assignments.data.Measurement;

import org.springframework.stereotype.Repository;


@Repository
public interface MeasurementRepository extends JpaRepository<Measurement, Long> {
}
