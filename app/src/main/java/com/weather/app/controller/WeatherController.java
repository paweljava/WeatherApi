package com.weather.app.controller;

import com.weather.app.dto.ResponseWeatherDto;
import com.weather.app.service.WeatherService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.weather.app.model.Mapper.mapRequestDtoToResponseDto;
import static com.weather.web.client.common.ExceptionMessages.INCORRECT_DATE_FORMAT_EXCEPTION_MESSAGE;
import static com.weather.web.client.utility.Checks.checkThat;

@RestController
public class WeatherController {
    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/weather/{date}")
    public ResponseWeatherDto getWeatherData(@PathVariable("date") String date) {
        checkThat(validateDateFormat(date), INCORRECT_DATE_FORMAT_EXCEPTION_MESSAGE);
        return mapRequestDtoToResponseDto(weatherService.getWeather(date));
    }

    private boolean validateDateFormat(String dateToValidate) {
        final var formatter = new SimpleDateFormat("yyyy-MM-dd");
        formatter.setLenient(false);
        final Date parsedData;
        try {
            parsedData = formatter.parse(dateToValidate);
            System.out.println("Date format yyyy-MM-dd " + formatter.format(parsedData));
            return true;
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
    }
}

