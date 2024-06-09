package ppj.assignments.beans;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ApiService {

    public void getWeather(String apiKey) {
        String location = "Prague";
        String urlString = "http://api.openweathermap.org/data/2.5/weather?q=" + location + "&appid=" + apiKey + "&units=metric";

        try {
            readData(urlString);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readData(String urlString) throws IOException {
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

