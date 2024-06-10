package ppj.assignments.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ppj.assignments.data.City;
import ppj.assignments.data.Country;
import ppj.assignments.repositories.CityRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CityService {

    @Autowired
    private CityRepository cityRepository;

    public List<City> getAllCities() {
        return cityRepository.findAll();
    }
     public City addCity(City city) {
        return cityRepository.save(city);
    }

    public City updateCity(String id, City updatedCity) {
        Optional<City> optionalCity = Optional.ofNullable(cityRepository.findByName(id));
        if (optionalCity.isPresent()) {
            City existingCity = optionalCity.get();
            existingCity.setName(updatedCity.getName());
            existingCity.setLatitude(updatedCity.getLatitude());
            existingCity.setLongitude(updatedCity.getLongitude());
            existingCity.setCountry(updatedCity.getCountry());
            return cityRepository.save(existingCity);
        } else {
            throw new RuntimeException("City not found with id: " + id);
        }
    }

     @Transactional
    public void deleteCity(String id) {
        cityRepository.deleteByName(id);
    }

}
