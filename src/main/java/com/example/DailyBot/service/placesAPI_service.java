package com.example.DailyBot.service;

import com.google.gson.Gson;
import com.fasterxml.jackson.databind.JsonNode;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class placesAPI_service {

    @Value("${google.api.key}")
    private String apiKey;

    public Object searchDinerByText(String textQuery) {
        Map<String, Object> requestMap = new HashMap<>();

        requestMap.put("textQuery", textQuery);

        Gson gson = new Gson();
        String requestBody = gson.toJson(requestMap);
        HttpHeaders headers = new HttpHeaders();

        headers.add("Content-Type", "application/json");
        headers.add("X-Goog-Api-Key", apiKey);
        headers.add(
                "X-Goog-FieldMask",
                "places.displayName.text," +
                        "places.googleMapsUri"
        );

        String requestUrl = "https://places.googleapis.com/v1/places:searchText";
        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody,headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<JsonNode> response = restTemplate.exchange(
                requestUrl,
                HttpMethod.POST,
                requestEntity,
                JsonNode.class);

        return response;
    }
}
