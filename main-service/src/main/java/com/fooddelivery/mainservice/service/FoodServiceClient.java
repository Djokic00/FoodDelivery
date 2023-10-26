package com.fooddelivery.mainservice.service;

import com.fooddelivery.mainservice.dto.request.OrderRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class FoodServiceClient {
    private final String foodServiceUrl;

    @Autowired
    public FoodServiceClient(@Value("${food-service-url}") String foodServiceUrl) {
        this.foodServiceUrl = foodServiceUrl;
    }

    public ResponseEntity<Boolean> checkFoodAvailability(OrderRequest orderRequest) {
        RestTemplate restTemplate = new RestTemplate();
        String url = foodServiceUrl + "/check-availability"; // Replace with the actual endpoint
        ResponseEntity<Boolean> response = restTemplate.postForEntity(url, orderRequest, Boolean.class);
        return response;
    }
}
