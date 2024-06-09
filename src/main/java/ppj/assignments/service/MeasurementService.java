package ppj.assignments.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ppj.assignments.data.City;
import ppj.assignments.data.Measurement;
import ppj.assignments.repositories.MeasurementRepository;

import java.util.List;

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

}
