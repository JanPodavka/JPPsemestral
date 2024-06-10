package ppj.assignments.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ppj.assignments.data.Measurement;
import ppj.assignments.repositories.MeasurementRepository;

import java.util.List;
import java.util.Optional;

@Service
public class MeasurementService {

    @Autowired
    private MeasurementRepository measurementRepository;

    public List<Measurement> getMeasurementsForCity(String cityName) {
        return measurementRepository.findByCityName(cityName);
    }

    public Measurement addMeasurement(Measurement measurement) {
        return measurementRepository.save(measurement);
    }

    public Measurement updateMeasurement(Long id, Measurement measurement) {
        Optional<Measurement> existingMeasurementOpt = measurementRepository.findById(id);
        if (existingMeasurementOpt.isPresent()) {
            Measurement existingMeasurement = existingMeasurementOpt.get();
            existingMeasurement.setTemperature(measurement.getTemperature());
            existingMeasurement.setWindSpeed(measurement.getWindSpeed());
            existingMeasurement.setHumidity(measurement.getHumidity());
            return measurementRepository.save(existingMeasurement);
        } else {
            throw new RuntimeException("Measurement not found");
        }
    }

    public void deleteMeasurement(Long id) {
        measurementRepository.deleteById(id);
    }

}
