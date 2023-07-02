package com.catsweatherbot.service;

import com.catsweatherbot.WeatherResponseDto;

public interface BotBackRestService {
    WeatherResponseDto getWeatherFromBackApi(String input, String userLanguage);
    // void createUser(String chatId); //when "/start"


}
