package com.catsweatherbot.service;

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
        URI url = new UriTemplate(userTableUrl + "/language").expand();

        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("chatId", chatId);
        requestBody.put("userLanguage", language);
        try {
            ResponseEntity<String> response = restTemplate.exchange(
                    url,
                    HttpMethod.PUT,
                    new HttpEntity<>(requestBody),
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
