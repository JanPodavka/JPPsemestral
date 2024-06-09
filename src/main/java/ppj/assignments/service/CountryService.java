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

    public Optional<Country> updateCountry(Long id, Country updatedCountry) {
        return countryRepository.findById(id).map(country -> {
            country.setName(updatedCountry.getName());
            country.setTimeZone(updatedCountry.getTimeZone());
            return countryRepository.save(country);
        });
    }

    public void deleteCountry(Long id) {
        countryRepository.deleteById(id);
    }
}
