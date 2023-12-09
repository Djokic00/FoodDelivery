package com.fooddelivery.mainservice.service;

import com.fooddelivery.shareddtoservice.dto.request.OrderRequest;
import com.fooddelivery.shareddtoservice.dto.response.FoodItemResponse;
import com.fooddelivery.shareddtoservice.dto.response.OrderResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class OrderServiceClient {
    private final String orderServiceUrl;

    @Autowired
    public OrderServiceClient(@Value("${order-service-url}") String orderServiceUrl) {
        this.orderServiceUrl = orderServiceUrl;
    }

    public ResponseEntity<Boolean> checkFoodAvailability(OrderRequest orderRequest) {
        RestTemplate restTemplate = new RestTemplate();
        String url = orderServiceUrl + "/food/check-availability";
        ResponseEntity<Boolean> response = restTemplate.postForEntity(url, orderRequest, Boolean.class);
        return response;
    }

    public ResponseEntity<OrderResponse> createOrder(OrderRequest orderRequest) {
        RestTemplate restTemplate = new RestTemplate();
        String url = orderServiceUrl + "/orders/create";
        ResponseEntity<OrderResponse> response = restTemplate.postForEntity(url, orderRequest, OrderResponse.class);
        return response;
    }
}

