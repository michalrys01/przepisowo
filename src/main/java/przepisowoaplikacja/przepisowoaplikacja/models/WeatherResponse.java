package przepisowoaplikacja.przepisowoaplikacja.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true) // Ignoruje nieznane właściwości przy deserializacji z formatu JSON.
public class WeatherResponse {

    private List<WeatherData> list; // Lista obiektów klasy WeatherData przechowujących informacje pogodowe.

    public List<WeatherData> getList() {
        return list;
    }

    public void setList(List<WeatherData> list) {
        this.list = list;
    }
}

//@JsonIgnoreProperties(ignoreUnknown = true): Adnotacja Jackson, która informuje parser JSON o ignorowaniu nieznanych właściwości przy deserializacji.
//private List<WeatherData> list;: Lista obiektów klasy WeatherData przechowujących informacje pogodowe.
//Metody get i set: Umożliwiają pobieranie i ustawianie wartości pola obiektu.