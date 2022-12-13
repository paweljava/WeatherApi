package com.weather.app.dto;
/*
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
public class ResponseWeatherDto {
    @JsonProperty("city_name")
    private String cityName;
    @JsonProperty("data")
    private List<WeatherDto> weatherDtoList;

    public ResponseWeatherDto() {
    }

    public ResponseWeatherDto(String cityName, List<WeatherDto> weatherDtoList) {
        this.cityName = cityName;
        this.weatherDtoList = weatherDtoList;
    }
}*/

import java.util.List;

public record ResponseWeatherDto(String cityName, List<WeatherDto> weatherDtoList) {
}
