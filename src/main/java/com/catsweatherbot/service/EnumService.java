package com.catsweatherbot.service;

import com.catsweatherbot.dictionary.response.EnAnswersEnum;
import com.catsweatherbot.dictionary.response.RuAnswersEnum;

public interface EnumService {


    default String chooseRightEnumAnswer(String lang, String command) {
        if ("ru".equals(lang)) {
            return RuAnswersEnum.getEnumByCommandName(command).getCommandReply();
        }
        return EnAnswersEnum.getEnumByCommandName(command).getCommandReply();
    }

    default String chooseRightEnumErrorMessage(String lang, String command) {
        if ("ru".equals(lang)) {
            return RuAnswersEnum.getEnumByCommandName(command).getCommandErrorMessage();
        }
        return EnAnswersEnum.getEnumByCommandName(command).getCommandErrorMessage();
    }

    default String chooseRightAnswerFromBack(String lang, String command) {
        if ("ru".equals(lang)) {
            return RuAnswersEnum.getEnumByCommandName(command).getCommandBackendReply();
        }
        return EnAnswersEnum.getEnumByCommandName(command).getCommandBackendReply();
    }
}
