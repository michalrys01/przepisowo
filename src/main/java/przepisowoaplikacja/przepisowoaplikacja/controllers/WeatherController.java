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

    @GetMapping("/weather")
    public String getWeather(@RequestParam(defaultValue = "Lublin") String city, Model model, RedirectAttributes redirectAttributes) {
        WeatherData weatherData = weatherService.getWeatherData(city);

        if (weatherData != null) {
            model.addAttribute("city", city);
            model.addAttribute("temperature", weatherData.getMain().get("temp"));
            model.addAttribute("wind", weatherData.getWind().get("speed"));
            return "weather";
        } else {
            // Dodaj atrybut do modelu z informacją o błędzie
            model.addAttribute("errorMessage", "Nie znaleziono danych pogodowych dla miasta: " + city);
            return "weather";
        }
    }
}