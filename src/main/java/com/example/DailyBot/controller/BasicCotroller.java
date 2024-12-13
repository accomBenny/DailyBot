package com.example.DailyBot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
public class BasicCotroller {

    @GetMapping("/healthcheck")
    public String healthcheck() {

        String result = new String("ok");

        return result;
    }
}
