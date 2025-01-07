package com.example.DailyBot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.DailyBot.service.placesAPI_service;

@RestController()
public class Basic_Controller {

    private final placesAPI_service placesAPI;

    //PlacesAPI 的服務
    public Basic_Controller(placesAPI_service placesAPI) {
        this.placesAPI = placesAPI;
    }

    @PostMapping("/healthcheck")
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
}
