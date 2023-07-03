package com.catsweatherbot.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.net.URI;

public interface HttpClient {
    <T> ResponseEntity<T> executeRequest(URI url, HttpMethod method, HttpEntity<?> requestBodyEntity, Class<T> responseType);
}
