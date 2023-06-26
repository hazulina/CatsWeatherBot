package com.catsweatherbot.service;

import com.catsweatherbot.WeatherToTg;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriTemplate;

import java.math.BigDecimal;
import java.net.URI;

@Service
public class BotBackRestServiceImp implements BotBackRestService {
    @Value("${weather.request.path}")
    private String weatherUrl;
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    @Override
    public String getWeatherFromBackApi(String input, String userLanguage) {
        URI url = new UriTemplate(weatherUrl).expand(input, userLanguage);
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        return convertJsonToTg(response.getBody(), userLanguage);
    }

    private String convertJsonToTg(String response, String userLanguage) {
        try {
            JsonNode node = objectMapper.readTree(response);
            if (node.has("message")) {
                return node.path("message").asText();
            }
            WeatherToTg tg = new WeatherToTg(
                    node.path("cityName").asText(),
                    node.path("weatherType").asText(),
                    node.path("description").asText(),
                    BigDecimal.valueOf(node.path("temperature").asDouble()),
                    BigDecimal.valueOf(node.path("feelsLikeTemp").asDouble()),
                    BigDecimal.valueOf(node.path("windSpeed").asDouble()));

            return "ru".equals(userLanguage) ? tg.setPrettyViewForOutputRu() : tg.setPrettyViewForOutputEn();


        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error parsing JSON", e);
        }
    }

    public BotBackRestServiceImp(RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }
}
