package ppj.assignments.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ppj.assignments.data.Country;
import ppj.assignments.repositories.CountryRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CountryService {
    @Autowired
    private CountryRepository countryRepository;

    public List<Country> getAllCountries() {
        return countryRepository.findAll();
    }
     public Country addCountry(Country country) {
        return countryRepository.save(country);
    }


     public Country updateCountry(String id, Country country) {
        Optional<Country> existingCountryOpt = countryRepository.findById(id);
        if (existingCountryOpt.isPresent()) {
            Country existingCountry = existingCountryOpt.get();
            existingCountry.setCountryCode(country.getCountryCode());
            return countryRepository.save(existingCountry);
        } else {
            throw new RuntimeException("Country not found");
        }
    }

    public void deleteCountry(String id) {
        countryRepository.deleteById(id);
    }


    }


