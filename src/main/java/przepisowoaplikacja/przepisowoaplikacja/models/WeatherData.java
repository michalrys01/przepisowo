package przepisowoaplikacja.przepisowoaplikacja.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true) // Ignoruje nieznane właściwości przy deserializacji z formatu JSON.
public class WeatherData {

    private Map<String, Object> main; // Mapa przechowująca informacje o głównych parametrach pogody (temperatura, ciśnienie itp.).
    private List<Map<String, Object>> weather; // Lista map przechowująca informacje o warunkach atmosferycznych (opis pogody, ikona itp.).
    private Map<String, Object> wind; // Mapa przechowująca informacje o wietrze (prędkość, kierunek itp.).

    public Map<String, Object> getMain() {
        return main;
    }

    public void setMain(Map<String, Object> main) {
        this.main = main;
    }

    public List<Map<String, Object>> getWeather() {
        return weather;
    }

    public void setWeather(List<Map<String, Object>> weather) {
        this.weather = weather;
    }

    public Map<String, Object> getWind() {
        return wind;
    }

    public void setWind(Map<String, Object> wind) {
        this.wind = wind;
    }
}
//@JsonIgnoreProperties(ignoreUnknown = true): Adnotacja Jackson, która informuje parser JSON o ignorowaniu nieznanych właściwości przy deserializacji.
//private Map<String, Object> main;: Mapa przechowująca informacje o głównych parametrach pogody (temperatura, ciśnienie itp.).
//private List<Map<String, Object>> weather;: Lista map przechowująca informacje o warunkach atmosferycznych (opis pogody, ikona itp.).
//private Map<String, Object> wind;: Mapa przechowująca informacje o wietrze (prędkość, kierunek itp.).
//Metody get i set: Umożliwiają pobieranie i ustawianie wartości pól obiektu.