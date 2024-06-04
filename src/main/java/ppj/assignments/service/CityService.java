package ppj.assignments.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ppj.assignments.repositories.CityRepository;

@Service
public class CityService {

    @Autowired
    private CityRepository cityRepository;

}
