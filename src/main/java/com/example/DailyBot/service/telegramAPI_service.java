package com.example.DailyBot.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.gson.Gson;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class telegramAPI_service {

    /*
    telegramBot 的 token
     */
    @Value("${telegram.api.key}")
    private String apikey;
    /*
    telegramBot 的 群組 id
     */
    @Value("${telegram.chat_id}")
    private String chatId;

    Gson gson = new Gson();

    /*
    發送按鈕訊息

     */
    public Object sendButtonMessage() {
        Map<String, Object> requestMap = new HashMap<>();

        // 定義按鈕
        Map<String, Object> optionButton1 = new HashMap<>();

        optionButton1.put("text", "選項1");
        optionButton1.put("callback_data", "1");

        Map<String, Object> optionButton2 = new HashMap<>();

        optionButton2.put("text", "選項2");
        optionButton2.put("callback_data", "2");

        Map<String, Object> optionMap = new HashMap<>();

        optionMap.put("inline_keyboard", new Map[][] {
                {
                    optionButton1,
                    optionButton2,
                }
        });

        requestMap.put("reply_markup", optionMap);
        requestMap.put("chat_id", chatId);
        requestMap.put("text", "請選擇一個選項:");

        String requestUrl = "https://api.telegram.org/bot" + apikey + "/sendMessage";
        String requestBody = gson.toJson(requestMap);
        HttpHeaders headers = new HttpHeaders();

        headers.add("Content-Type", "application/json");

        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody,headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<JsonNode> response ;

        try {
            response = restTemplate.exchange(
                    requestUrl,
                    HttpMethod.POST,
                    requestEntity,
                    JsonNode.class
            );
        }catch (HttpClientErrorException e){

            return e.getResponseBodyAsString();
        }catch (Exception e){

            return e.getMessage();
        }

        return response.getBody();
    }
}
