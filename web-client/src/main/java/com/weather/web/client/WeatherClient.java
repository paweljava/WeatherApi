package com.weather.web.client;

import com.weather.web.client.dto.WeatherBitResponseDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import static com.weather.web.client.common.ExceptionMessages.NO_RESPONSE_FROM_API_EXCEPTION_MESSAGE;
import static com.weather.web.client.utility.Checks.checkThat;

@Component
public class WeatherClient {
    @Value("${api_host}")
    private String apiHost;
    // not valid API_KEY
    // public static final String API_KEY = "f1cf185d994f48bc8ece50716d0ade8d";
    // valid API_KEY
    public static final String API_KEY = "bf6acaa02f9b4bbea4b7325e06f70d43";
    private final RestTemplate restTemplate = new RestTemplate();

    public WeatherBitResponseDto getForecastByCoordinates(double lat, double lon) {
        final var response = callGetMethod(
                "/v2.0/forecast/daily?lat={lat}&lon={lon}&key={API_KEY}&lang=pl&",
                WeatherBitResponseDto.class, lat, lon, API_KEY);
        checkThat(!response.data().isEmpty(), NO_RESPONSE_FROM_API_EXCEPTION_MESSAGE);
        return response;
    }

    private <T> T callGetMethod(String url, Class<T> responseType, Object... objects) {
        return restTemplate.getForObject(this.apiHost + url, responseType, objects);
    }
}