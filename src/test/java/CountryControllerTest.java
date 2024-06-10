import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ppj.assignments.Main;
import ppj.assignments.data.Country;
import ppj.assignments.repositories.CountryRepository;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = Main.class, properties = "spring.config.name=application-devel")
@AutoConfigureMockMvc
public class CountryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    public void testAddCountry() throws Exception {
        Country country = new Country();
        country.setCountryCode("CZ");

        mockMvc.perform(MockMvcRequestBuilders.post("/api/country")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(country)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.countryCode").value("CZ"));
    }

    @Test
    public void testUpdateCountry() throws Exception {
        Country country = new Country();
        country.setCountryCode("CZ");
        Country savedCountry = countryRepository.save(country);

        Country updatedCountry = new Country();
        updatedCountry.setCountryCode("CZ");

        mockMvc.perform(MockMvcRequestBuilders.put("/api/country/{id}", savedCountry.getCountryCode())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedCountry)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.countryCode").value("CZ"));
    }

    @Test
    public void testDeleteCountry() throws Exception {
        Country country = new Country();
        country.setCountryCode("CP");
        Country savedCountry = countryRepository.save(country);

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/country/{id}", savedCountry.getCountryCode()))
                .andExpect(status().isOk());


        mockMvc.perform(MockMvcRequestBuilders.get("/api/country"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[*].countryCode", not(contains(savedCountry.getCountryCode()))));
    }
}
