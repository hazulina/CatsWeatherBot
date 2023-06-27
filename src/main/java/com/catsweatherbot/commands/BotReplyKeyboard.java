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
        replyKeyboardMarkup = createReplyKeyboardMarkup();
    }

    private InlineKeyboardMarkup createReplyKeyboardMarkup() {
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();

        List<InlineKeyboardButton> buttons = new ArrayList<>();

        buttons.add(createInlineKeyboardButton("Eng", "en"));
        buttons.add(createInlineKeyboardButton("Rus", "ru"));

        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();
        keyboard.add(buttons);

        markup.setKeyboard(keyboard);

        return markup;
    }

    private InlineKeyboardButton createInlineKeyboardButton(String text, String callbackData) {
        InlineKeyboardButton button = new InlineKeyboardButton(text);
        button.setCallbackData(callbackData);
        return button;
    }
}
