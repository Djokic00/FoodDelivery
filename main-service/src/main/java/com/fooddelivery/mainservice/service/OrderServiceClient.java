package com.fooddelivery.mainservice.service;

import com.fooddelivery.mainservice.dto.request.OrderRequest;
import com.fooddelivery.mainservice.dto.response.OrderResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderServiceClient {
    private final String orderServiceUrl;

    @Autowired
    public OrderServiceClient(@Value("${order-service-url}") String orderServiceUrl) {
        this.orderServiceUrl = orderServiceUrl;
    }

    public ResponseEntity<OrderResponse> createOrder(OrderRequest orderRequest) {
        RestTemplate restTemplate = new RestTemplate();
        String url = orderServiceUrl + "/create";
        ResponseEntity<OrderResponse> response = restTemplate.postForEntity(url, orderRequest, OrderResponse.class);
        return response;
    }
}

