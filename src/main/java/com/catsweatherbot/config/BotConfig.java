package com.catsweatherbot.config;

import com.catsweatherbot.Bot;
import com.catsweatherbot.MessageHandler;
import com.catsweatherbot.commands.BotReplyKeyboard;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.api.methods.updates.SetWebhook;

@Configuration
@AllArgsConstructor

public class BotConfig {
    private final TelegramConfig telegramConfig;
    private final MessageHandler messageHandler;
    private final BotReplyKeyboard botReplyKeyboard;

    @Bean
    public SetWebhook setWebhookInstance() {
        return SetWebhook.builder().url(telegramConfig.getWebhookPath()).build();
    }

    @Bean
    public Bot springWebhookBot(SetWebhook setWebhook) {
        return new Bot(setWebhook, telegramConfig, messageHandler, botReplyKeyboard);
    }
}
