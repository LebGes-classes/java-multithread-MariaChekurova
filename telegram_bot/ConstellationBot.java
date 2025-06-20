package org.example.telegram_bot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.MonthDay;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

public class ConstellationBot extends TelegramLongPollingBot {
    @Override
    public String getBotUsername() {
        return "ZodiacConstellationsBot";
    }

    @Override
    public String getBotToken() {
        return "7805415867:AAE_5R7toqEBU3Qip5Ii6-wChp3dWLvalq0";
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();

            if (messageText.equals("/start")) {
                sendMessage(chatId, "Привет! Я бот для определения знака зодиака по дате рождения! " +
                        "\n Используй команду /getZodiacSign, чтобы определить знак зодиака. \n" +
                        "Формат сообщения: /getZodiacSign ДД.ММ.ГГГГ");
            }
            else if (messageText.startsWith("/getZodiacSign")) {
                String date = messageText.substring(14);
                getSign(chatId, date);
            }
            else {
                sendMessage(chatId, "К сожалению, такой команды нет(");
            }
        }
    }

    private void getSign(long chatId, String possibleDate){
        possibleDate = possibleDate.trim();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.uuuu").withResolverStyle(ResolverStyle.STRICT);
        try {
            LocalDate inputDate = LocalDate.parse(possibleDate, dateFormatter);
            MonthDay monthDay = MonthDay.from(inputDate);
            String zodiacSign = ZodiacInformation.getSign(monthDay);
            String information = ZodiacInformation.getSignInformation(zodiacSign);
            sendMessage(chatId, information);
        } catch (DateTimeParseException e) {
            sendMessage(chatId, "Вы ввели некорректную дату, даты " + possibleDate + " не существует");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void sendMessage(long chatId, String text) {
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText(text);
        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
