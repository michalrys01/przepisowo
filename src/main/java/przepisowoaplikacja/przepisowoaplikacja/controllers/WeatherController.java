package przepisowoaplikacja.przepisowoaplikacja.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import przepisowoaplikacja.przepisowoaplikacja.models.WeatherData;
import przepisowoaplikacja.przepisowoaplikacja.services.WeatherService;

@Controller
public class WeatherController {

    @Autowired
    private WeatherService weatherService;

    // Metoda obsługująca żądanie GET na pobranie danych pogodowych
    @GetMapping("/weather")
    public String getWeather(@RequestParam(defaultValue = "Lublin") String city, Model model, RedirectAttributes redirectAttributes) {
        // Wywołuje serwis do pobrania danych pogodowych dla podanego miasta
        WeatherData weatherData = weatherService.getWeatherData(city);

        // Sprawdza, czy udało się pobrać dane pogodowe
        if (weatherData != null) {
            // Dodaje atrybuty do modelu z danymi pogodowymi
            model.addAttribute("city", city);
            model.addAttribute("temperature", weatherData.getMain().get("temp"));
            model.addAttribute("wind", weatherData.getWind().get("speed"));
            // Zwraca nazwę widoku strony pogody
            return "weather";
        } else {
            // Dodaje atrybut do modelu z informacją o błędzie
            model.addAttribute("errorMessage", "Nie znaleziono danych pogodowych dla miasta: " + city);
            // Zwraca nazwę widoku strony pogody
            return "weather";
        }
    }
}


//@Controller: Oznacza klasę jako kontroler w Spring MVC.
//@Autowired: Automatycznie wstrzykuje zależność do serwisu WeatherService.
//@GetMapping("/weather"): Oznacza, że ta metoda obsługuje żądania HTTP GET na stronę pogody ("/weather").
//public String getWeather(@RequestParam(defaultValue = "Lublin") String city, Model model, RedirectAttributes redirectAttributes): Metoda obsługująca żądanie GET, pobiera dane pogodowe dla podanego miasta.
//WeatherData weatherData = weatherService.getWeatherData(city): Wywołuje serwis do pobrania danych pogodowych dla podanego miasta.
//if (weatherData != null) { ... } else { ... }: Sprawdza, czy udało się pobrać dane pogodowe.
//model.addAttribute("city", city): Dodaje atrybuty do modelu z danymi pogodowymi.
//return "weather";: Zwraca nazwę widoku strony pogody.
//model.addAttribute("errorMessage", "Nie znaleziono danych pogodowych dla miasta: " + city): Dodaje atrybut do modelu z informacją o błędzie.
//return "weather";: Zwraca nazwę widoku strony pogody.