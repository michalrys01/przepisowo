package przepisowoaplikacja.przepisowoaplikacja.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;

import java.util.List;
import java.util.Map;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherData {
    private Map<String, Object> main;
    private List<Map<String, Object>> weather;
    private Map<String, Object> wind;

    public void setMain(Map<String, Object> main) {
        this.main = main;
    }

    public void setWeather(List<Map<String, Object>> weather) {
        this.weather = weather;
    }

    public void setWind(Map<String, Object> wind) {
        this.wind = wind;
    }
}
