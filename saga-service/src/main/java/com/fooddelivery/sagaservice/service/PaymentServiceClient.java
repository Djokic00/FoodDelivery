package com.fooddelivery.sagaservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.Map;

@Service
public class PaymentServiceClient {
    private final String paymentServiceUrl;

    @Autowired
    public PaymentServiceClient(@Value("${payment-service-url}") String paymentServiceUrl) {
        this.paymentServiceUrl = paymentServiceUrl;
    }

    public ResponseEntity<Void> simulateRefund(Long orderId) {
        RestTemplate restTemplate = new RestTemplate();
        String url = paymentServiceUrl + "/refund";

        Map<String, Long> requestBody = Collections.singletonMap("orderId", orderId);

        // Send the orderId in the request body
        ResponseEntity<Void> response = restTemplate.postForEntity(url, requestBody, Void.class);
        return response;
    }
}
