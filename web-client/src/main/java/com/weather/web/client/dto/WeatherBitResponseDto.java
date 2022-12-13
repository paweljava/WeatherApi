package com.weather.web.client.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.weather.web.client.model.Weather;

import java.util.List;

/*
import com.fasterxml.jackson.annotation.JsonProperty;
import com.weather.web.client.model.Weather;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class WeatherBitResponseDto {

    @JsonProperty("city_name")
    private String city_name;
    @JsonProperty("data")
    private List<Weather> data;

    public WeatherBitResponseDto() {
    }

    public WeatherBitResponseDto(String city_name, List<Weather> data) {
        this.city_name = city_name;
        this.data = data;
    }

    public String getCity_name() {
        return city_name;
    }

    public List<Weather> getData() {
        return data;
    }
}*/
public record WeatherBitResponseDto(@JsonProperty("city_name") String cityName, List<Weather> data) {
}