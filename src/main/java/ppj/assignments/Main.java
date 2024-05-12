package ppj.assignments;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ppj.assignments.configs.AppConfiguration;
import ppj.assignments.writer.Writer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;


@SpringBootApplication
public class Main {

    public static void main(String[] args) throws Exception {


        SpringApplication app = new SpringApplication(Main.class);
        app.setAdditionalProfiles("devel");
        ApplicationContext ctx = app.run(args);

        AppConfiguration cfg = ctx.getBean(AppConfiguration.class);
        Writer writer = ctx.getBean(Writer.class);
        writer.write(cfg.toString());



        //// zde pouze dotazy pro získání informací
        getWeather();



    }
    public static void getWeather() throws IOException {
        String API_KEY = "6c116b843f6d300eccfcc05acafb7f3f";
        String LOCATION = "Prague,cz";
        String urlString = "http://api.openweathermap.org/data/2.5/weather?q=" + LOCATION + "&appid=" + API_KEY + "&units=metric";

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
    }


}