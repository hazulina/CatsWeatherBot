package com.catsweatherbot.service;

import com.catsweatherbot.WeatherResponseDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriTemplate;

import java.net.URI;

@Service
public class BotBackRestServiceImp implements BotBackRestService {
    @Value("${weather.request.path}")
    private String weatherUrl;
    private final RestTemplate restTemplate;

    @Override
    public WeatherResponseDto getWeatherFromBackApi(String input, String userLanguage) {
        URI url = new UriTemplate(weatherUrl).expand(input, userLanguage);
        ResponseEntity<WeatherResponseDto> response = restTemplate.getForEntity(url, WeatherResponseDto.class);
        return response.getBody();
    }

    public BotBackRestServiceImp(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
}
