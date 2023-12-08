package com.fooddelivery.mainservice.service;

import com.fooddelivery.shareddtoservice.dto.request.PaymentRequest;
import com.fooddelivery.shareddtoservice.dto.response.PaymentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Profile({"dev", "prod"})
@PropertySource("classpath:application-${spring.profiles.active}.yaml")
public class PaymentServiceClient {
    private final String paymentServiceUrl;

    @Autowired
    public PaymentServiceClient(@Value("${payment-service-url}") String paymentServiceUrl) {
        this.paymentServiceUrl = paymentServiceUrl;
    }

    public ResponseEntity<PaymentResponse> processPayment(PaymentRequest paymentRequest) {
        RestTemplate restTemplate = new RestTemplate();
        String url = paymentServiceUrl + "/process";
        ResponseEntity<PaymentResponse> response = restTemplate.postForEntity(url, paymentRequest, PaymentResponse.class);
        return response;
    }
}
