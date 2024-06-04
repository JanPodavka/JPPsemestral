package ppj.assignments.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ppj.assignments.repositories.MeasurementRepository;

@Service
public class MeasurementService {

    @Autowired
    private MeasurementRepository measurementRepository;

}
