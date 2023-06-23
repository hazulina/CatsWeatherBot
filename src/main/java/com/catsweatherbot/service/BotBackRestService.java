package com.catsweatherbot.service;

public interface BotBackRestService {
    String getWeatherFromBackApi(String input, String userLanguage);
    // void createUser(String chatId); //when "/start"


}
