import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ppj.assignments.Main;
import ppj.assignments.data.City;
import ppj.assignments.data.Country;
import ppj.assignments.data.Measurement;
import ppj.assignments.repositories.CityRepository;
import ppj.assignments.repositories.CountryRepository;
import ppj.assignments.service.MeasurementService;
import ppj.assignments.beans.WeatherService;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest(classes = Main.class, properties = "spring.config.name=application-devel")
@AutoConfigureMockMvc
public class MeasurementControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MeasurementService measurementService;

    @Autowired
    private WeatherService weatherService;

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private CountryRepository countryRepository;


    @Test
    public void testGetMeasurementsForCities() throws Exception {
        String cityName = "New York";

        mockMvc.perform(MockMvcRequestBuilders.get("/api/measurement")
                .param("city", cityName))
                .andExpect(status().isOk());
    }

    @Test
    public void testAddMeasurement() throws Exception {
        Measurement measurement = new Measurement();

        Country country = new Country();
        country.setCountryCode("CZ");
        country = countryRepository.save(country);

        City city = new City();
        city.setName("Testing");
        city.setLongitude(5);
        city.setCountry(country);
        city = cityRepository.save(city);

        measurement.setCity(city);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/measurement")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(measurement)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.city.longitude").value(5));
    }

     @Test
    public void testDeleteMeasurement() throws Exception {
        Measurement measurement = new Measurement();

        Country country = new Country();
        country.setCountryCode("CZ");
        country = countryRepository.save(country);

        City city = new City();
        city.setName("Testing");
        city.setLongitude(5);
        city.setCountry(country);
        city = cityRepository.save(city);

        measurement.setCity(city);
        Measurement savedMeasurement = measurementService.addMeasurement(measurement);

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/measurement/{id}", savedMeasurement.getId()))
                .andExpect(status().isOk());
    }

    @Test
    public void testUpdateMeasurement() throws Exception {
        Measurement measurement = new Measurement();

        Country country = new Country();
        country.setCountryCode("CZ");
        country = countryRepository.save(country);

        City city = new City();
        city.setName("Testing");
        city.setLongitude(5);
        city.setCountry(country);
        city = cityRepository.save(city);

        measurement.setCity(city);
        Measurement savedMeasurement = measurementService.addMeasurement(measurement);

        savedMeasurement.setTemperature(25.5);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/measurement/{id}", savedMeasurement.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(savedMeasurement)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.temperature").value(25.5));
    }

     @Test
    public void testGetMeasurementForDay() throws Exception {
        String cityName = "New York";

        mockMvc.perform(MockMvcRequestBuilders.get("/api/measurement/current")
                .param("city", cityName))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetMeasurementForWeek() throws Exception {
        String cityName = "Prague";

        mockMvc.perform(MockMvcRequestBuilders.get("/api/measurement/week")
                .param("city", cityName))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetMeasurementForTwoweeks() throws Exception {
        String cityName = "Prague";

        mockMvc.perform(MockMvcRequestBuilders.get("/api/measurement/twoweeks")
                .param("city", cityName))
                .andExpect(status().isOk());
    }
}


