package com.weather.app.model;

import com.weather.app.dto.ResponseWeatherDto;
import com.weather.app.dto.WeatherDto;
import com.weather.web.client.dto.WeatherBitResponseDto;

import java.util.stream.Collectors;

public class Mapper {
    public static ResponseWeatherDto mapRequestDtoToResponseDto(WeatherBitResponseDto weatherBitResponseDto) {
        return new ResponseWeatherDto(
                weatherBitResponseDto.cityName(),
                weatherBitResponseDto.data().stream().map(data -> new WeatherDto(
                                data.forecastDate(),
                                data.averageTemp(),
                                data.windSpeed()))
                        .collect(Collectors.toList()));
    }
}