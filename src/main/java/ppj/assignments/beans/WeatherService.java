package ppj.assignments.beans;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import ppj.assignments.data.City;
import ppj.assignments.data.Country;
import ppj.assignments.repositories.CityRepository;
import ppj.assignments.repositories.CountryRepository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WeatherService {

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private CityRepository cityRepository;



    public void getActualWeatherByCity(String apiKey, String location) {
        String urlString = "http://api.openweathermap.org/data/2.5/weather?q=" + location + "&appid=" + apiKey + "&units=metric";

        try {
            StringBuilder data = readData(urlString);
            System.out.println(data);
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(data.toString());
            JsonNode lon = rootNode.path("coord").path("lon");
            JsonNode lat = rootNode.path("coord").path("lat");
            JsonNode temp = rootNode.path("main").path("temp");
            JsonNode country = rootNode.path("sys").path("country");
            JsonNode city = rootNode.path("name");
            JsonNode dt = rootNode.path("dt");
            JsonNode id = rootNode.path("sys").path("id");
            System.out.println(temp.asText());

            ///Database část
            if (!country.isMissingNode() && !city.isMissingNode()) {
                String countryCode = country.asText();
                String cityName = city.asText();
                System.out.println(countryCode);
                // Check if country exists, if not, add it
                 if (!countryRepository.existsByCountryCode(countryCode)){
                   Country newCountry = new Country();
                    newCountry.setCountryCode(countryCode);
                    countryRepository.save(newCountry);
                    System.out.println("New country: " + newCountry.getCountryCode());
                }

                Country existingCountry = countryRepository.findByCountryCode(country.asText());

                if (!cityRepository.existsByName(city.asText())) {
                    City newCity = new City();
                    newCity.setName(cityName);
                    newCity.setLatitude(lat.floatValue());
                    newCity.setLongitude(lon.floatValue());
                    newCity.setCountry(existingCountry);
                    cityRepository.save(newCity);
                    System.out.println("New city: " + newCity.getName());

                }
            }



        } catch (IOException e) {
            e.printStackTrace();
        }
    }

     public void getWeatherByDate(String apiKey) {
        String location = "Prague,cz";
        String urlString = "https://history.openweathermap.org/data/2.5/history/city?lat=41.85&lon=-87.65&appid=" + apiKey;

        try {
            StringBuilder data = readData(urlString);
            System.out.println(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private StringBuilder readData(String urlString) throws IOException {
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        int responseCode = connection.getResponseCode();
        System.out.println("Response Code: " + responseCode);
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            System.out.println(line);
            response.append(line);
        }

        reader.close();
        connection.disconnect();
        System.out.println(response);
        return response;
    }

}

