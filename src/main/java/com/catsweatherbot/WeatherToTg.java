package com.catsweatherbot;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.math.BigDecimal;

@AllArgsConstructor
@Data
public class WeatherToTg {
    private String cityName;
    private String weatherType;
    private String description;
    private BigDecimal temperature;
    private BigDecimal feelsLikeTemp;
    private BigDecimal windSpeed;


    public String setPrettyViewForOutputEn() {
        return "<b><u>" + cityName + "</u></b>" + "\n" +
                "description: " + description + "\n" +
                "temperature is " + temperature + "°C" + "\n" +
                "but it feels like " + feelsLikeTemp + "\n" +
                "and wind speed is " + windSpeed + "\n";

    }

    public String setPrettyViewForOutputRu() {
        return "<b><u>" + cityName + "</u></b>" + "\n" +
                "сейчас: " + description + "\n" +
                "температура воздуха: " + temperature + "°C" + "\n" +
                "ощущается как: " + feelsLikeTemp + "\n" +
                "скорость ветра: " + windSpeed + "\n";

    }
}
