package com.fooddelivery.orderservice.service;

import com.fooddelivery.shareddtoservice.dto.request.OrderRequest;
import com.fooddelivery.shareddtoservice.dto.response.OrderResponse;
import com.fooddelivery.shareddtoservice.dto.response.OrderResponseList;
public interface FoodOrderService {
    OrderResponse createOrder(OrderRequest orderRequest);
    OrderResponseList getActiveOrders();
    void cancelOrder(Long orderId);
}