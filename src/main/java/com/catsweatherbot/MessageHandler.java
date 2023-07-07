package com.catsweatherbot;

import com.catsweatherbot.commands.BotReplyKeyboard;
import com.catsweatherbot.dictionary.response.EnAnswersEnum;
import com.catsweatherbot.service.BotBackRestService;
import com.catsweatherbot.service.CommandBackService;
import com.catsweatherbot.service.EnumService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.io.ByteArrayInputStream;

@Component
@Data
@AllArgsConstructor
public class MessageHandler {

    private final BotReplyKeyboard botReplyKeyboard;
    private BotBackRestService botBackRestService;
    private CommandBackService commandBackService;

    public String handleUpdate(Update update, String chatId, EnumService enumService) {
        if (EnAnswersEnum.START.getCommandName().equals(update.getMessage().getText())) {
            if (commandBackService.addUserToBackDb(chatId).is2xxSuccessful()) {
                return enumService.chooseRightEnumAnswer(update.getMessage().getText());
            }
        }
        return enumService.chooseRightEnumErrorMessage(update.getMessage().getText());
    }

    public String handleCallback(CallbackQuery query, EnumService enumService) {
        if (commandBackService
                .updateUserLanguage(query.getMessage().getChatId().toString(), query.getData()).is2xxSuccessful()) {
            return enumService.chooseRightAnswerFromBack("/chooselanguage");
        }
        return enumService.chooseRightEnumErrorMessage("/chooselanguage");
    }

    public String getUserLanguage(String chatId) {
        if (commandBackService.getUserLanguage(chatId).isEmpty()) {
            commandBackService.addUserToBackDb(chatId);
        }
        return commandBackService.getUserLanguage(chatId);
    }

    public SendPhoto sendPhotoWithWeather(String chatId, String text, String userLanguage, EnumService enumService) {
        WeatherResponseDto weatherResponseDto = botBackRestService.getWeatherFromBackApi(text, userLanguage);
        ByteArrayInputStream inputStream = new ByteArrayInputStream(weatherResponseDto.getPictureFromCloudStorage());
        SendPhoto sendPhoto = new SendPhoto();
        sendPhoto.setChatId(chatId);
        sendPhoto.setParseMode("HTML");
        sendPhoto.setPhoto(new InputFile(inputStream, "photo"));
        if ("cityNotFound".equals(weatherResponseDto.getWeatherType())) {
            sendPhoto.setCaption(enumService.chooseRightAnswerFromBack("wrongInput"));
            return sendPhoto;
        }
        if ("ru".equals(userLanguage)) {
            sendPhoto.setCaption(weatherResponseDto.setPrettyViewForOutputRu());
        } else {
            sendPhoto.setCaption(weatherResponseDto.setPrettyViewForOutputEn());
        }
        System.out.println(sendPhoto.getCaption());
        return sendPhoto;
    }


}
