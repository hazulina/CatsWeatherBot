package com.catsweatherbot.dictionary.response;

import com.catsweatherbot.service.EnumService;
import lombok.Getter;

@Getter
public enum RuAnswersEnum implements EnumService {
    START("/start",
            "Привет, я Бот, что рассказывает о погоде и показывает котиков!",
            "Теперь мы знакомы, чем могу помочь?",
            "Мы уже начали работу) Давай продолжим"),
    LANGUAGE("/chooselanguage",
            "Какой язык установить?",
            "Установлен русский язык",
            "Случилось неожиданное! Ошибка в установке языка"),
    FAVOURITE_CITY("/favouritecity",
            "Укажите название города для ежедневной рассылки погоды",
            "Любимый город установлен",
            "Город не найден! Возможно он слишком мал. Выберите другой город рядом с Вами, пожалуйста"),
    WRONG_INPUT("wrongInput",
            "Ошибка ввода! Допускаются только буквы :)",
            "Ошибка ввода! Город не найден. Попробуйте снова.",
            ""),
    INSTANCE("", "", "", "");

    public static EnumService getInstance() {
        return INSTANCE;
    }

    private final String commandName;
    private final String commandReply;
    private final String commandBackendReply;
    private final String commandErrorMessage;

    RuAnswersEnum(String commandName, String commandReply, String commandBackendReply, String commandErrorMessage) {
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

    public static RuAnswersEnum getEnumByCommandName(String commandName) {
        for (RuAnswersEnum command : RuAnswersEnum.values()) {
            if (commandName.equals(command.getCommandName())) {
                return command;
            }
        }
        throw new IllegalArgumentException(String.format("WRONG COMMAND INPUT >%s<", commandName));
    }
}
