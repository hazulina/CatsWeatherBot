package com.catsweatherbot;

import com.catsweatherbot.commands.BotReplyKeyboard;
import com.catsweatherbot.config.TelegramConfig;
import com.catsweatherbot.dictionary.response.EnAnswersEnum;
import com.catsweatherbot.dictionary.response.RuAnswersEnum;
import com.catsweatherbot.service.EnumService;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updates.SetWebhook;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ForceReplyKeyboard;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.starter.SpringWebhookBot;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Bot extends SpringWebhookBot {

    private final TelegramConfig config;
    private final MessageHandler messageHandler;
    private final BotReplyKeyboard botReplyKeyboard;

    @Override
    @SneakyThrows
    public BotApiMethod<?> onWebhookUpdateReceived(Update update) {
        if (update.hasCallbackQuery()) {
            onWebhookCallbackReceived(update.getCallbackQuery());
        }
        if (update.getMessage() != null && update.getMessage().hasText()) {
            String chatId = update.getMessage().getChatId().toString();
            String userLanguage = messageHandler.getUserLanguage(chatId);
            if (update.getMessage().isReply()) {
                System.out.println("It's reply to message " + update.getMessage().getReplyToMessage().getMessageId());
            } else {
                handleUpdateWithTextMessage(update, chatId, userLanguage);
            }
        }
        return null;
    }


    public void onWebhookCallbackReceived(CallbackQuery callbackQuery) {
        String chatId = callbackQuery.getMessage().getChatId().toString();
        String userLanguage = callbackQuery.getData();
        try {
            execute(new SendMessage(chatId, messageHandler.handleCallback(callbackQuery, getEnumService(userLanguage))));
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }


    private void handleUpdateWithTextMessage(Update update, String chatId, String userLanguage) {
        switch (update.getMessage().getText()) {
            case "/start":
                try {
                    execute(new SendMessage(chatId, messageHandler.handleUpdate(update, chatId, getEnumService(userLanguage))));
                } catch (TelegramApiException e) {
                    throw new RuntimeException(getEnumService(userLanguage).chooseRightEnumErrorMessage(update.getMessage().getText()));
                }
                break;
            case "/chooselanguage":
                try {
                    SendMessage message = new SendMessage(chatId,
                            getEnumService(userLanguage).chooseRightEnumAnswer(update.getMessage().getText()));
                    message.setReplyMarkup(botReplyKeyboard.getReplyKeyboardMarkup());

                    execute(message);
                } catch (TelegramApiException e) {
                    throw new RuntimeException(getEnumService(userLanguage)
                            .chooseRightEnumErrorMessage(update.getMessage().getText()));
                }
                break;
            case "/favouritecity":
                try {
                    SendMessage message = new SendMessage(chatId, getEnumService(userLanguage)
                            .chooseRightEnumAnswer(update.getMessage().getText()));
                    ForceReplyKeyboard forceReplyKeyboard = new ForceReplyKeyboard();
                    message.setReplyMarkup(forceReplyKeyboard);
                    execute(message);
                } catch (TelegramApiException e) {
                    throw new RuntimeException(getEnumService(userLanguage)
                            .chooseRightEnumErrorMessage(update.getMessage().getText()));
                }
                break;
            default:
                try {
                    execute(messageHandler.sendPhotoWithWeather(chatId, update.getMessage().getText(), userLanguage,getEnumService(userLanguage)));
                } catch (TelegramApiException e) {
                    throw new RuntimeException(e);
                }
        }
    }


    private boolean isInputCorrect(String input) {
        return input.matches("^[a-zA-Z/]*$") || input.matches("^[а-яА-Я/]*$");
    }

    private EnumService getEnumService(String lang) {
        if ("ru".equals(lang)) {
            return RuAnswersEnum.getInstance();
        }
        return EnAnswersEnum.getInstance();
    }


    @Override
    public String getBotPath() {
        return config.getWebhookPath();
    }

    @Override
    public String getBotUsername() {
        return config.getBotName();
    }

    public Bot(SetWebhook setWebhook, TelegramConfig config,
               MessageHandler messageHandler, BotReplyKeyboard botReplyKeyboard) {
        super(setWebhook, config.getBotToken());
        this.config = config;
        this.messageHandler = messageHandler;
        this.botReplyKeyboard = botReplyKeyboard;
    }

    public Bot(DefaultBotOptions options, SetWebhook setWebhook, String botToken, TelegramConfig config,
               MessageHandler messageHandler, BotReplyKeyboard botReplyKeyboard) {
        super(options, setWebhook, botToken);
        this.config = config;
        this.messageHandler = messageHandler;
        this.botReplyKeyboard = botReplyKeyboard;
    }
}
