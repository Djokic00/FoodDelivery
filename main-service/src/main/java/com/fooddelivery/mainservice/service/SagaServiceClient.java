package com.fooddelivery.mainservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.Map;

@Service
public class SagaServiceClient {
    private final String sagaServiceUrl;

    @Autowired
    public SagaServiceClient(@Value("${saga-service-url}") String sagaServiceUrl) {
        this.sagaServiceUrl = sagaServiceUrl;
    }

    public ResponseEntity<Void> initiateCompensation(Long orderId) {
        RestTemplate restTemplate = new RestTemplate();
        Map<String, Long> requestBody = Collections.singletonMap("orderId", orderId);
        String url = sagaServiceUrl + "/compensate";
        return restTemplate.postForEntity(url, requestBody, Void.class);
    }
}
