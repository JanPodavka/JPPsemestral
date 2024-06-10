package ppj.assignments.beans;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import ppj.assignments.configs.AppConfiguration;
import ppj.assignments.data.City;
import ppj.assignments.data.Country;
import ppj.assignments.data.Measurement;
import ppj.assignments.repositories.CityRepository;
import ppj.assignments.repositories.CountryRepository;
import ppj.assignments.repositories.MeasurementRepository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class WeatherService {

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private CityRepository cityRepository;
    @Autowired
    private MeasurementRepository measurementRepository;

    @Autowired
    private AppConfiguration appConfiguration;


    private static final String HISTORY_API_BASE_URL = "https://history.openweathermap.org/data/2.5/history/city";



    public List<Measurement> getMeasurementForDay(String city){
       return getActualWeatherByCity(city);

    }
    public List<Measurement> getMeasurementForWeek(String city) throws IOException {
        long endTimestamp = System.currentTimeMillis() / 1000;
        long startTimestamp = endTimestamp - 7 * 24 * 60 * 60;
        getActualWeatherByCity(city);
        return getHistoricalWeather(city, startTimestamp, endTimestamp);

    }
    public List<Measurement> getMeasurementForTwoweeks(String city) throws IOException {
        long endTimestamp = System.currentTimeMillis() / 1000;
        long startTimestamp = endTimestamp - 14 * 24 * 60 * 60;
        getActualWeatherByCity(city);
        return getHistoricalWeather(city, startTimestamp, endTimestamp);
    }




     public List<Measurement> getHistoricalWeather(String city, long startTimestamp, long endTimestamp) throws IOException {
        String apiKey = appConfiguration.getApiKeyHistory();
        String urlString = String.format("%s?q=%s&start=%d&end=%d&appid=%s", HISTORY_API_BASE_URL, city, startTimestamp, endTimestamp, apiKey);
        return GetWeatherData(urlString, city);
    }
     private List<Measurement> GetWeatherData(String urlString, String city) throws IOException {
         URL url = new URL(urlString);
         HttpURLConnection connection = (HttpURLConnection) url.openConnection();
         connection.setRequestMethod("GET");

         int responseCode = connection.getResponseCode();
         ArrayList<Measurement> measurements = null;
         if (responseCode == HttpURLConnection.HTTP_OK) {
             BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
             StringBuilder response = new StringBuilder();
             String line;

             while ((line = reader.readLine()) != null) {
                 response.append(line);
                 System.out.println(line);
             }


             try {
                 ObjectMapper objectMapper = new ObjectMapper();
                 JsonNode rootNode = objectMapper.readTree(String.valueOf(response));

                 JsonNode listNode = rootNode.get("list");
                 measurements = new ArrayList<>();
                 City existingCity = cityRepository.findByName(city);
                 if (listNode.isArray()) {

                     List<Double> temperatures = new ArrayList<>(); // Store temperatures to compute mean

                     for (JsonNode item : listNode) {
                         long dt = item.get("dt").asLong();
                         double temp = item.get("main").get("temp").asDouble();
                         int humidity = item.get("main").get("humidity").asInt();
                         double windSpeed = item.get("wind").get("speed").asDouble();

                         System.out.println("DateTime: " + dt);
                         System.out.println("Temperature: " + temp);
                         System.out.println("Humidity: " + humidity);
                         System.out.println("Wind Speed: " + windSpeed);
                         System.out.println("-------------");
                         Measurement measurement = new Measurement();
                         measurement.setCity(existingCity);
                         measurement.setTimestamp(new Timestamp(dt * 1000));
                         measurement.setTemperature(temp);
                         measurement.setWindSpeed(windSpeed);
                         measurement.setHumidity(humidity);
                         System.out.println(measurement.getTemperature());
                         measurementRepository.save(measurement);
                         measurements.add(measurement);
                         temperatures.add(temp);
                     }
                     // This is for mean
                      double sum = 0;
                    for (double temp : temperatures) {
                        sum += temp;
                    }
                    double meanTemperature = sum / temperatures.size();

                    Measurement meanMeasurement = new Measurement();
                    meanMeasurement.setCity(existingCity);
                    meanMeasurement.setTimestamp(new Timestamp(System.currentTimeMillis()));
                    meanMeasurement.setTemperature(meanTemperature);
                    meanMeasurement.setWindSpeed(0);
                    meanMeasurement.setHumidity(0);
                    //measurementRepository.save(meanMeasurement);
                    measurements.add(0, meanMeasurement);

                 }
             } catch (IOException e) {
                 e.printStackTrace();
             }
             reader.close();
         } else {
             throw new IOException("Failed to fetch data, response code: " + responseCode);
         }
         return measurements;
     }





    public List<Measurement> getActualWeatherByCity(String city) {
        String apiKey = appConfiguration.getApiKey();
        String urlString = "http://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + apiKey + "&units=metric";

        List<Measurement> measurements = null;
        try {
            StringBuilder data = readData(urlString);
            System.out.println(data);
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(data.toString());
            JsonNode lon = rootNode.path("coord").path("lon");
            JsonNode lat = rootNode.path("coord").path("lat");
            JsonNode temp = rootNode.path("main").path("temp");
            JsonNode country = rootNode.path("sys").path("country");
            JsonNode city_name = rootNode.path("name");
            JsonNode wind = rootNode.path("wind").path("speed");
            JsonNode humidity = rootNode.path("main").path("humidity");
            JsonNode dt = rootNode.path("dt");

            ///Database část
            if (!country.isMissingNode() && !city_name.isMissingNode()) {
                String countryCode = country.asText();
                String cityName = city_name.asText();
                if (!countryRepository.existsByCountryCode(countryCode)) {
                    Country newCountry = new Country();
                    newCountry.setCountryCode(countryCode);
                    countryRepository.save(newCountry);
                    System.out.println("New country: " + newCountry.getCountryCode());
                }

                Country existingCountry = countryRepository.findByCountryCode(country.asText());

                if (!cityRepository.existsByName(city_name.asText())) {
                    City newCity = new City();
                    newCity.setName(cityName);
                    newCity.setLatitude(lat.floatValue());
                    newCity.setLongitude(lon.floatValue());
                    newCity.setCountry(existingCountry);
                    cityRepository.save(newCity);
                    System.out.println("New city: " + newCity.getName());

                }
            }
            City existingCity = cityRepository.findByName(city_name.asText());

            measurements = new ArrayList<>();
            Measurement measurement = new Measurement();
            measurement.setCity(existingCity);
            measurement.setTimestamp(new Timestamp(dt.asLong() * 1000));
            measurement.setTemperature(temp.asDouble());
            measurement.setWindSpeed(wind.asDouble());
            measurement.setHumidity(humidity.asInt());
            System.out.println(measurement.getTemperature());
            measurementRepository.save(measurement);
            measurements.add(measurement);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return measurements;
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

