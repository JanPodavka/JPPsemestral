package ppj.assignments;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import ppj.assignments.beans.ApiService;
import ppj.assignments.beans.DatabaseService;
import ppj.assignments.configs.AppConfiguration;




@SpringBootApplication
@EnableJpaRepositories(basePackages = "ppj.assignments.repositories")
public class Main {


    public static void main(String[] args) throws Exception {


        SpringApplication app = new SpringApplication(Main.class);
        app.setAdditionalProfiles("devel");
        ApplicationContext ctx = app.run(args);

        AppConfiguration cfg = ctx.getBean(AppConfiguration.class);


        String apiKey = cfg.getApiKey();
        String apiKeyHistory = cfg.getApiKeyHistory();

        ApiService apiService = ctx.getBean(ApiService.class);
        apiService.getWeather(apiKey);






        //// zde pouze dotazy pro získání informací
        //getWeather(apiKey);
        //getWeatherByDate(apiKeyHistory);


    }
    @Bean
    public ApiService apiService() {
        return new ApiService();
    }

    @Bean
    public DatabaseService databaseService() {
        return new DatabaseService();
    }
//    public static void readData(String urlString) throws IOException {
//        URL url = new URL(urlString);
//        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//        connection.setRequestMethod("GET");
//
//        int responseCode = connection.getResponseCode();
//        System.out.println("Response Code: " + responseCode);
//
//        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//        StringBuilder response = new StringBuilder();
//        String line;
//
//        while ((line = reader.readLine()) != null) {
//            System.out.println(line);
//            response.append(line);
//        }
//
//        reader.close();
//    }
//
//
//    public static void getWeatherByDate(String API_key) throws IOException {
//        String LOCATION = "Prague,cz";
//        String urlString = "https://history.openweathermap.org/data/2.5/history/city?lat=41.85&lon=-87.65&appid=" + API_key;
//        readData(urlString);
//    }
//
//    public static void getWeather(String API_key) throws IOException {
//        String LOCATION = "Prague";
//        String urlString = "http://api.openweathermap.org/data/2.5/weather?q=" + LOCATION + "&appid=" + API_key + "&units=metric";
//
//        readData(urlString);
//    }
//
//
}