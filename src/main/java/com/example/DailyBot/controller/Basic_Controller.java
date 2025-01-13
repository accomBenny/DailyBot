package com.example.DailyBot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.DailyBot.service.placesAPI_service;
import com.example.DailyBot.service.telegramAPI_service;

@RestController()
public class Basic_Controller {
    //Google 的 places_API 服務
    private final placesAPI_service placesAPI;
    //TelegramBot 的機器人服務
    private final telegramAPI_service telegramAPI;

    public Basic_Controller(
            placesAPI_service placesAPI,
            telegramAPI_service telegramAPI
    ) {
        this.placesAPI = placesAPI;
        this.telegramAPI = telegramAPI;
    }

    @GetMapping("/healthcheck")
    public String healthcheck() {
        return new String("ok");
    }

    //文字搜尋餐廳
    @GetMapping("/dinerRates")
    public Object dinerRates(
        @RequestParam String textQuery) {
        Object response = placesAPI.searchDinerByText(textQuery);

        return response;
    }

    //TG 發送含有按鈕的訊息
    @GetMapping("/start")
    public Object sendTelegramMessage() {
        Object response = telegramAPI.sendButtonMessage();

        return response;
    }
}
