package com.catsweatherbot.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriTemplate;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@Service
public class CommandBackServiceImp implements CommandBackService {
    @Value("${user.request.path}")
    private String userTableUrl;
    private final RestTemplate restTemplate;

    public HttpStatusCode addUserToBackDb(String chatId) {
        URI url = new UriTemplate(userTableUrl).expand();
        try {
            ResponseEntity<String> response = restTemplate.exchange(
                    url,
                    HttpMethod.POST,
                    new HttpEntity<>(chatId),
                    String.class);
            return response.getStatusCode();
        } catch (HttpClientErrorException e) {
            return e.getStatusCode();
        }
    }

    @Override
    public HttpStatusCode updateUserLanguage(String chatId, String language) {
        Map<String, String> map = new HashMap<>();
        map.put("chatId", chatId);
        map.put("userLanguage", language);
        URI url = new UriTemplate(userTableUrl + "/language").expand();
        try {
            ResponseEntity<String> response = restTemplate.exchange(
                    url,
                    HttpMethod.PUT,
                    new HttpEntity<>(map),
                    String.class
            );
            return response.getStatusCode();
        } catch (HttpClientErrorException e) {
            return e.getStatusCode();
        }
    }

    @Override
    public String getUserLanguage(String chatId) {
        URI url = new UriTemplate(userTableUrl + "/" + chatId + "/language").expand();
        try {
            ResponseEntity<String> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    new HttpEntity<>(chatId),
                    String.class);
            return response.getBody();
        } catch (HttpClientErrorException e) {
            return e.getStatusCode().toString();
        }
    }

    public CommandBackServiceImp(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
}
