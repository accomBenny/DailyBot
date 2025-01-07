package com.example.DailyBot.service;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class placesAPI_service {

    @Value("${google.api.key}")
    private String apiKey;

    public Object searchDinerByText(String textQuery, float latitude, float longitude) {

        String requestBody = """
        {
            "textQuery": "%s",
            "locationBias": {
                "circle": {
                    "center": {
                        "latitude": %f,
                        "longitude": %f
                    },
                    "radius": 3000
                }
            }
        }
        """.formatted(textQuery, latitude, longitude);


        HttpHeaders headers = new HttpHeaders();

        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        headers.add("X-Goog-Api-Key", apiKey);
        headers.add(
                "X-Goog-FieldMask",
                "places.displayName.text," +
                        "places.googleMapsUri"
        );

        String requestUrl = "https://places.googleapis.com/v1/places:searchText";


        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody,headers);

        RestTemplate restTemplate = new RestTemplate();

        try{
            ResponseEntity<JsonNode> response = restTemplate.exchange(
                    requestUrl,
                    HttpMethod.POST,
                    requestEntity,
                    JsonNode.class
            );

            return response;
        }catch(Exception e){
//            ObjectMapper objectMapper = new ObjectMapper();
//            ObjectNode errorNode = objectMapper.createObjectNode();
//            errorNode.put("details", e.getMessage().replaceAll("<EOL>", "\\n"));
            return e.getMessage();
        }
    }
}
