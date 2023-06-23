package com.catsweatherbot.service;

import org.springframework.http.HttpStatusCode;

public interface CommandBackService {
    HttpStatusCode addUserToBackDb(String chatId);

    HttpStatusCode updateUserLanguage(String chatId, String language);

    String getUserLanguage(String chatId);
}
