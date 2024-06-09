package ppj.assignments.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ppj.assignments.data.City;
import ppj.assignments.data.Country;
import ppj.assignments.repositories.CityRepository;

import java.util.List;

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
}
