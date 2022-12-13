package com.weather.app.service;

import com.weather.app.repository.CityRepository;
import com.weather.web.client.WeatherClient;
import com.weather.web.client.dto.WeatherBitResponseDto;
import com.weather.web.client.model.Weather;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static com.weather.app.service.WeatherValidator.validateWeatherConditions;
import static com.weather.web.client.common.ExceptionMessages.CITY_NOT_FOUND_FOR_REQUESTED_WEATHER_CONDITIONS_EXCEPTION_MESSAGE;
import static com.weather.web.client.utility.Checks.checkThat;

@Service
@Slf4j
public class WeatherService {

    private final WeatherClient weatherClient;
    private final CityRepository cityRepository;

    public WeatherService(WeatherClient weatherClient, CityRepository cityRepository) {
        this.weatherClient = weatherClient;
        this.cityRepository = cityRepository;
    }

    @Cacheable(cacheNames = "requestWeather")
    public WeatherBitResponseDto getWeather(String date) {
        final var response = cityRepository.getCityList().stream().map(
                        city -> weatherClient.getForecastByCoordinates(city.getLat(), city.getLon()))
                .collect(Collectors.toList());
        return getCityWeather(getCitiesWeathers(response, date));
    }

    private List<WeatherBitResponseDto> getCitiesWeathers(List<WeatherBitResponseDto> cityWeathers, String data) {
        final List<WeatherBitResponseDto> dataList = new ArrayList<>(Collections.emptyList());
        for (final var cityData : cityWeathers) {
            final var cityName = cityData.cityName();
            for (final var weatherData : cityData.data()) {
                if ((weatherData.forecastDate().equals(data)) &&
                        (validateWeatherConditions(weatherData.averageTemp(), weatherData.windSpeed()))) {
                    final var newWeatherData = new Weather(
                            weatherData.forecastDate(),
                            weatherData.averageTemp(),
                            weatherData.windSpeed());
                    List<Weather> weatherList = new ArrayList<>();
                    weatherList.add(newWeatherData);

                    var newCityData = new WeatherBitResponseDto(cityName, weatherList);
                    dataList.add(newCityData);
                }

            }
        }
        checkThat(dataList.size() > 0, CITY_NOT_FOUND_FOR_REQUESTED_WEATHER_CONDITIONS_EXCEPTION_MESSAGE);
        return dataList;
    }

    public WeatherBitResponseDto getCityWeather(List<WeatherBitResponseDto> cityWeathers) {
        WeatherBitResponseDto response = null;
        double formulaValue = 0;
        for (final var cityData : cityWeathers) {
            for (final var weather : cityData.data()) {
                if (applyFormula(weather.averageTemp(), weather.windSpeed()) > formulaValue) {
                    formulaValue = (applyFormula(weather.averageTemp(), weather.windSpeed()));
                    response = new WeatherBitResponseDto(cityData.cityName(), cityData.data());
                }
            }
        }
        return response;
    }

    private double applyFormula(double averageTemp, double windSpeed) {
        return averageTemp + windSpeed * 3;
    }
}