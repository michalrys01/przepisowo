package przepisowoaplikacja.przepisowoaplikacja.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import przepisowoaplikacja.przepisowoaplikacja.models.WeatherData;
import przepisowoaplikacja.przepisowoaplikacja.models.WeatherResponse;

@Service
public class WeatherService {

    @Value("${weather.api.url}")
    private String apiUrl;

    @Value("${weather.api.appId}")
    private String apiKey;

    public WeatherData getWeatherData(String city) {
        String fullApiUrl = apiUrl + "?q=" + city + "&appid=" + apiKey + "&units=metric";

        RestTemplate restTemplate = new RestTemplate();

        try {
            WeatherResponse weatherResponse = restTemplate.getForObject(fullApiUrl, WeatherResponse.class);

            if (weatherResponse != null && weatherResponse.getList() != null && !weatherResponse.getList().isEmpty()) {
                return weatherResponse.getList().get(0);
            }
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                System.out.println("Użytkownik podał złą lokalizację");
            } else {
                e.printStackTrace();
            }
        }

        return null;
    }
}
