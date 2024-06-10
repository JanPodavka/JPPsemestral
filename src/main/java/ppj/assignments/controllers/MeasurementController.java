package ppj.assignments.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ppj.assignments.beans.WeatherService;
import ppj.assignments.data.Measurement;
import ppj.assignments.service.MeasurementService;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/measurement")
public class MeasurementController {

    @Autowired
    private MeasurementService measurementService;
    @Autowired
    private WeatherService weatherService;



    @GetMapping
    public List<Measurement> getMeasurementsForCities(@RequestParam String city) {
        return measurementService.getMeasurementsForCity(city);
    }

    @PostMapping
    public Measurement addMeasurement(@RequestBody Measurement measurement) {
        return measurementService.addMeasurement(measurement);
    }

    @GetMapping("/{id}")
    public Measurement getMeasurement(@PathVariable Long id, @RequestBody Measurement measurement) {
        return measurementService.updateMeasurement(id, measurement);
    }

    @PutMapping("/{id}")
    public Measurement updateMeasurement(@PathVariable Long id, @RequestBody Measurement measurement) {
        return measurementService.updateMeasurement(id, measurement);
    }

    @DeleteMapping("/{id}")
    public void deleteMeasurement(@PathVariable Long id) {
        measurementService.deleteMeasurement(id);
    }

    //displaying methods


    @GetMapping("/current")
    public List<Measurement> getMeasurementForDay(@RequestParam String city) {
        return weatherService.getMeasurementForDay(city);

    }

    @GetMapping("/week")
    public List<Measurement> getMeasurementForWeek(@RequestParam String city) throws IOException {
        return weatherService.getMeasurementForWeek(city);

    }

    @GetMapping("/twoweeks")
    public List<Measurement> getMeasurementForTwoweeks(@RequestParam String city) throws IOException {
        return weatherService.getMeasurementForTwoweeks(city);
    }


}
