package ppj.assignments.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ppj.assignments.data.City;
import ppj.assignments.data.Country;
import ppj.assignments.data.Measurement;
import ppj.assignments.service.CountryService;
import ppj.assignments.service.MeasurementService;

import java.util.List;

@RestController
@RequestMapping("/api/measurement")
public class MeasurementController {

    @Autowired
    private MeasurementService measurementService;


    @GetMapping
    public List<Measurement> getMeasurementsForCities(@RequestParam String city) {
        return measurementService.getMeasurementsForCity(city);
    }

    @PostMapping
    public Measurement addMeasurement(@RequestBody Measurement measurement) {
        return measurementService.addMeasurement(measurement);
    }

}
