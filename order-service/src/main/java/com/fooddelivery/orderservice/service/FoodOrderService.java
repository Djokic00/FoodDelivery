package com.fooddelivery.orderservice.service;

import com.fooddelivery.orderservice.dto.request.FoodOrderRequest;
import com.fooddelivery.orderservice.dto.response.FoodOrderResponse;

public interface FoodOrderService {
    FoodOrderResponse createOrder(FoodOrderRequest orderRequest);
}