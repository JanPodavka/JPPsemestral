package ppj.assignments.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ppj.assignments.data.City;
import ppj.assignments.data.Country;
import ppj.assignments.service.CityService;
import ppj.assignments.service.CountryService;

import java.util.List;

@RestController
@RequestMapping("/api/city")
public class CityController {
    @Autowired
    private CityService cityService;


    @GetMapping
    public List<City> getAllCites() {
        return cityService.getAllCities();
    }
    @PostMapping
    public City addCity(@RequestBody City city) {
        return cityService.addCity(city);
    }
}
