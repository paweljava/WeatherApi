package com.weather.app.dto;
/*
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class WeatherDto {
    @JsonProperty("valid_date")
    private String data;
    @JsonProperty("temp")
    private double averageTemp;
    @JsonProperty("wind_spd")
    private double windSpeed;

    public WeatherDto(String data, double averageTemp, double windSpeed) {
        this.data = data;
        this.averageTemp = averageTemp;
        this.windSpeed = windSpeed;
    }
}*/

import com.fasterxml.jackson.annotation.JsonProperty;

public record WeatherDto(@JsonProperty("valid_date") String data,
                         @JsonProperty("temp") double averageTemp,
                         @JsonProperty("wind_spd") double windSpeed) {
}
