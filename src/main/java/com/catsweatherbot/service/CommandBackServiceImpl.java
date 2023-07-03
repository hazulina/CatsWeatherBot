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
public class CommandBackServiceImpl implements CommandBackService {
    @Value("${user.request.path}")
    private String userTableUrl;
    private final HttpClient httpClient;

    public HttpStatusCode addUserToBackDb(String chatId) {
        URI url = new UriTemplate(userTableUrl).expand();
        return httpClient.executeRequest(url, HttpMethod.POST,
                new HttpEntity<>(chatId), String.class).getStatusCode();
    }

    @Override
    public HttpStatusCode updateUserLanguage(String chatId, String language) {
        URI url = new UriTemplate(userTableUrl + "/language").expand();

        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("chatId", chatId);
        requestBody.put("userLanguage", language);

        return httpClient.executeRequest(url, HttpMethod.PUT,
                new HttpEntity<>(requestBody), String.class).getStatusCode();
    }

    @Override
    public String getUserLanguage(String chatId) {
        URI url = new UriTemplate(userTableUrl + "/" + chatId + "/language").expand();
        ResponseEntity<String> response = httpClient.executeRequest(url, HttpMethod.GET,
                new HttpEntity<>(chatId), String.class);
        return response.getBody() != null ? response.getBody() : response.getStatusCode().toString();
    }

    public CommandBackServiceImpl(HttpClient httpClient) {
        this.httpClient = httpClient;
    }
}
