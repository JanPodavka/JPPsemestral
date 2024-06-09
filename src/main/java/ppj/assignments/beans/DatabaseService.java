package ppj.assignments.beans;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class DatabaseService {


    public void getWeatherByDate(String apiKey) {
        String location = "Prague,cz";
        String urlString = "https://history.openweathermap.org/data/2.5/history/city?lat=41.85&lon=-87.65&appid=" + apiKey;

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
