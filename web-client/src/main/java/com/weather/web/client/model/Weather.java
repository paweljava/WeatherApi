package com.weather.web.client.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/*import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

@Getter
@NoArgsConstructor
public class Weather {

    @JsonProperty("valid_date")
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private String forecastDate;
    @JsonProperty("temp")
    private double averageTemp;
    @JsonProperty("wind_spd")
    private double windSpeed;

    public Weather(String valid_date, double temp, double wind_spd) {
        this.forecastDate = valid_date;
        this.averageTemp = temp;
        this.windSpeed = wind_spd;
    }

    @Override
    public String toString() {
        return "WeatherDto{" +
                "forecast date = " + forecastDate +
                ", average temperature = " + averageTemp +
                ", wind speed = " + windSpeed +
                '}';
    }
}*/
public record Weather(@JsonProperty("valid_date") String forecastDate,
                      @JsonProperty("temp") double averageTemp,
                      @JsonProperty("wind_spd") double windSpeed) {
}
