package com.catsweatherbot.dictionary.response;

import com.catsweatherbot.service.EnumService;
import lombok.Getter;

@Getter
public enum EnAnswersEnum implements EnumService {
    START("/start",
            "Hi, I'm Cats & Weather Bot!",
            "Nice to meet you. What can I do for you?",
            "You've already started me!"),
    LANGUAGE("/chooselanguage",
            "What language should be set?",
            "Language is set to English",
            "Something suspicious is happen..."),
    FAVOURITE_CITY("/favouritecity",
            "Send me city's name which weather you'd like to get every day",
            "Favourite city is set",
            "City not found! Maybe it is too small. Choose another one, please"),
    WRONG_INPUT("wrongInput",
            "Wrong Input! Only letters are allowed.",
            "Wrong input! City not found. Try again, please",
            "Extremely wrong input)"),
    INSTANCE("", "", "", "");

    public static EnumService getInstance() {
        return INSTANCE;
    }

    private final String commandName;
    private final String commandReply;
    private final String commandBackendReply;
    private final String commandErrorMessage;

    EnAnswersEnum(String commandName, String commandReply, String commandBackendReply, String commandErrorMessage) {
        this.commandName = commandName;
        this.commandReply = commandReply;
        this.commandBackendReply = commandBackendReply;
        this.commandErrorMessage = commandErrorMessage;
    }

    @Override
    public String chooseRightEnumAnswer(String command) {
        return getEnumByCommandName(command).getCommandReply();
    }

    @Override
    public String chooseRightEnumErrorMessage(String command) {
        return getEnumByCommandName(command).getCommandErrorMessage();
    }

    @Override
    public String chooseRightAnswerFromBack(String command) {
        return getEnumByCommandName(command).getCommandBackendReply();
    }

    public static EnAnswersEnum getEnumByCommandName(String commandName) {
        for (EnAnswersEnum command : EnAnswersEnum.values()) {
            if (commandName.equals(command.getCommandName())) {
                return command;
            }
        }
        throw new IllegalArgumentException(String.format("WRONG COMMAND INPUT >%s<", commandName));
    }
}
