package com.catsweatherbot.service;

public interface EnumService {

    String chooseRightEnumAnswer(String command);

    String chooseRightEnumErrorMessage(String command);

    String chooseRightAnswerFromBack(String command);
}
