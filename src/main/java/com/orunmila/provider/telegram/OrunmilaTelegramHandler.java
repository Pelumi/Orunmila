package com.orunmila.provider.telegram;

import com.orunmila.config.BotConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.TelegramApiException;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;

/**
 * @author Pelumi<pelumi@maven.ai>
 *         Created on 28/04/16 at 06:42.
 */
public class OrunmilaTelegramHandler extends TelegramLongPollingBot {
    private static final Logger logger = LoggerFactory.getLogger(OrunmilaTelegramHandler.class);

    public OrunmilaTelegramHandler() {
        logger.info("Initializing Orunmila Telegram handler...");
    }

    public void onUpdateReceived(Update update) {

        logger.info("Update received, type is [{}]", update.getUpdateId());
        logger.info("The update received is: [{}]", update.getMessage());
        logger.info("Update.hasMessage returns: [{}]", update.hasMessage());
        //check if the update has a message
        if (update.hasMessage()) {
            Message message = update.getMessage();
            logger.info("message.hasText returns: [{}]", message.hasText());

            //TODO abstract detecting the different types of messages into an enum
            //check if the message has text. it could also  contain for example a location ( message.hasLocation() )
            if (message.hasText()) {
                logger.info("Text message received from user: [{}] with content [{}]", message.getFrom().getFirstName(), message.getText());
                //create a object that contains the information to send back the message
                SendMessage sendMessageRequest = new SendMessage();
                sendMessageRequest.setChatId(message.getChatId().toString()); //who should get the message? the sender from which we got the message...
                sendMessageRequest.setText("You said: " + message.getText());
                sendMessageRequest.setText("I say go fuck yourself");
                try {
                    sendMessage(sendMessageRequest); //at the end, so some magic and send the message ;)
                } catch (TelegramApiException e) {
                    logger.error("There was an exception sending a message through telegram's API.");
                }
            }
        }
    }

    public String getBotUsername() {
        return BotConfig.ORUNMILA_NAME;
    }

    public String getBotToken() {
        return BotConfig.TELEGRAM_ORUNMILA_TOKEN;
    }

}
