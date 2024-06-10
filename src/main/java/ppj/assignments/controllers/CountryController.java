package ppj.assignments.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ppj.assignments.data.Country;
import ppj.assignments.service.CountryService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/country")
public class CountryController {
    @Autowired
    private CountryService countryService;


    @GetMapping
    public List<Country> getAllCountries() {
        return countryService.getAllCountries();
    }
    @PostMapping
    public Country addCountry(@RequestBody Country country) {
        return countryService.addCountry(country);
    }

@PutMapping("/{id}")
    public Country updateCountry(@PathVariable String id, @RequestBody Country country) {
        return countryService.updateCountry(id, country);
    }

    @DeleteMapping("/{id}")
    public void deleteCountry(@PathVariable String id) {
        countryService.deleteCountry(id);
    }
}

