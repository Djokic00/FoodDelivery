package com.fooddelivery.sagaservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.Map;

@Service
public class OrderServiceClient {
    private final String orderServiceUrl;

    @Autowired
    public OrderServiceClient(@Value("${order-service-url}") String orderServiceUrl) {
        this.orderServiceUrl = orderServiceUrl;
    }

    public ResponseEntity<Void> cancelOrder(Long orderId) {
        RestTemplate restTemplate = new RestTemplate();
        String url = orderServiceUrl + "/cancelOrder";

        Map<String, Long> requestBody = Collections.singletonMap("orderId", orderId);

        ResponseEntity<Void> response = restTemplate.postForEntity(url, requestBody, Void.class);
        return response;
    }
}
