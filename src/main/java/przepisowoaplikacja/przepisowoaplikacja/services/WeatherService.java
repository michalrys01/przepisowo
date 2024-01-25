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
    // Serwis do obsługi integracji z API pogodowego.

    @Value("${weather.api.url}")
    private String apiUrl;  // Adres URL do API pogodowego pobierany z konfiguracji.

    @Value("${weather.api.appId}")
    private String apiKey;  // Klucz API pogodowego pobierany z konfiguracji.

    public WeatherData getWeatherData(String city) {
        // Metoda do pobierania danych pogodowych dla danego miasta.

        // Skonstruowanie pełnego adresu URL z uwzględnieniem miasta i klucza API.
        String fullApiUrl = apiUrl + "?q=" + city + "&appid=" + apiKey + "&units=metric";

        RestTemplate restTemplate = new RestTemplate();  // Obiekt do wykonywania żądań HTTP.

        try {
            // Wywołanie żądania GET do API pogodowego i uzyskanie odpowiedzi w formie obiektu WeatherResponse.
            WeatherResponse weatherResponse = restTemplate.getForObject(fullApiUrl, WeatherResponse.class);

            // Sprawdzenie, czy odpowiedź jest niepusta i zawiera dane pogodowe.
            if (weatherResponse != null && weatherResponse.getList() != null && !weatherResponse.getList().isEmpty()) {
                // Zwrócenie pierwszego elementu z listy danych pogodowych (można dostosować do własnych potrzeb).
                return weatherResponse.getList().get(0);
            }
        } catch (HttpClientErrorException e) {
            // Obsługa błędów HTTP, np. gdy podane miasto nie istnieje.
            if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                System.out.println("Użytkownik podał złą lokalizację");
            } else {
                e.printStackTrace();
            }
        }

        return null;  // Zwrócenie wartości null w przypadku braku danych pogodowych.
    }
}


//@Service: Oznacza klasę jako komponent Springa, będący serwisem, który wykonuje logikę biznesową.
//@Value("${weather.api.url}"): Wstrzyknięcie adresu URL do API pogodowego z pliku konfiguracyjnego.
//@Value("${weather.api.appId}"): Wstrzyknięcie klucza API pogodowego z pliku konfiguracyjnego.
//private String apiUrl;: Pole przechowujące adres URL do API pogodowego.
//private String apiKey;: Pole przechowujące klucz API pogodowego.
//public WeatherData getWeatherData(String city) {...}: Metoda do pobierania danych pogodowych dla danego miasta.
//String fullApiUrl = apiUrl + "?q=" + city + "&appid=" + apiKey + "&units=metric";: Skonstruowanie pełnego adresu URL z uwzględnieniem miasta i klucza API.
//RestTemplate restTemplate = new RestTemplate();: Utworzenie obiektu do wykonywania żądań HTTP.
//WeatherResponse weatherResponse = restTemplate.getForObject(fullApiUrl, WeatherResponse.class);: Wywołanie żądania GET do API pogodowego i uzyskanie odpowiedzi w formie obiektu WeatherResponse.
//if (weatherResponse != null && weatherResponse.getList() != null && !weatherResponse.getList().isEmpty()) { ... }: Sprawdzenie, czy odpowiedź zawiera dane pogodowe.
//return weatherResponse.getList().get(0);: Zwrócenie pierwszego elementu z listy danych pogodowych (można dostosować do własnych potrzeb).
//} catch (HttpClientErrorException e) { ... }: Obsługa błędów HTTP, np. gdy podane miasto nie istnieje.
//return null;: Zwrócenie wartości null w przypadku braku danych pogodowych.