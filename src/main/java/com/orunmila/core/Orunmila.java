package com.orunmila.core;

import com.orunmila.provider.telegram.OrunmilaTelegramHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.TelegramApiException;
import org.telegram.telegrambots.TelegramBotsApi;

/**
 * @author Pelumi<pelumi@maven.ai>
 *         Created on 28/04/16 at 06:44.
 */
public class Orunmila {

    private static final Logger logger = LoggerFactory.getLogger(Orunmila.class);

    public static void main(String[] args) {

        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            telegramBotsApi.registerBot(new OrunmilaTelegramHandler());
        } catch (TelegramApiException e) {
            logger.error("Exception registering Orunmila Telegram handler: ", e);
        }
    }

}
