package com.catsweatherbot.commands;

import lombok.Data;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

@Component
@Data
public class BotReplyKeyboard {
    private final InlineKeyboardMarkup replyKeyboardMarkup;

    public BotReplyKeyboard() {
        replyKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();
        List<InlineKeyboardButton> row = new ArrayList<>();
        InlineKeyboardButton en = new InlineKeyboardButton("Eng");
        en.setCallbackData("en");
        InlineKeyboardButton ru = new InlineKeyboardButton("Rus");
        ru.setCallbackData("ru");
        row.add(en);
        row.add(ru);
        keyboard.add(row);
        replyKeyboardMarkup.setKeyboard(keyboard);
    }


}
