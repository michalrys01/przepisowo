package przepisowoaplikacja.przepisowoaplikacja.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import przepisowoaplikacja.przepisowoaplikacja.models.WeatherData;
import przepisowoaplikacja.przepisowoaplikacja.services.WeatherService;

@Controller
public class WeatherController {

    @Autowired
    private WeatherService weatherService;

    @GetMapping("/weather")
    public String getWeather(@RequestParam(defaultValue = "Lublin") String city, Model model) {
        WeatherData weatherData = weatherService.getWeatherData(city);

        if (weatherData != null) {
            model.addAttribute("city", city);
            model.addAttribute("temperature", weatherData.getMain().get("temp"));
            model.addAttribute("wind", weatherData.getWind().get("speed"));
        }

        return "weather";
    }
}
