package ppj.assignments.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ppj.assignments.repositories.CountryRepository;

@Service
public class CountryService {
    @Autowired
    private CountryRepository countryRepository;
}
