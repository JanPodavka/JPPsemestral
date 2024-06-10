
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ppj.assignments.Main;
import ppj.assignments.data.City;
import ppj.assignments.data.Country;
import ppj.assignments.repositories.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = Main.class, properties = "spring.config.name=application-devel")

@AutoConfigureMockMvc
public class CityControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testGetAllCities() throws Exception {
        mockMvc.perform(get("/api/city"))
                .andExpect(status().isOk());
    }

   @Test
public void testAddCity() throws Exception {
       Country country = new Country();
       country.setCountryCode("CZ");
       country = countryRepository.save(country);

       City city = new City();
       city.setName("Liberec");
       city.setCountry(country);

       mockMvc.perform(post("/api/city")
                       .contentType(MediaType.APPLICATION_JSON)
                       .content(objectMapper.writeValueAsString(city)))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.name").value("Liberec"));
   }

 @Test
public void testUpdateCity() throws Exception {
    // Create a country
    Country country = new Country();
    country.setCountryCode("CZ");
    country = countryRepository.save(country);

    // Create a city
    City city = new City();
    city.setName("Testing");
    city.setLongitude(5);
    city.setCountry(country);
    city = cityRepository.save(city);

    City updatedCity = new City();
    updatedCity.setName("Testing");
    updatedCity.setLongitude(6);
    updatedCity.setCountry(country);

    mockMvc.perform(put("/api/city/" + city.getName())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedCity)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.longitude").value(6));
}

@Test
public void testDeleteCity() throws Exception {
    // Create a country
    Country country = new Country();
    country.setCountryCode("CZ");
    countryRepository.save(country);

    City city = new City();
    city.setName("Lisabon");
    city.setCountry(country);

    // Save the city
    cityRepository.save(city);
    mockMvc.perform(delete("/api/city/" + city.getName()))
            .andExpect(status().isOk());
}

}
