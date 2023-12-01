package com.fooddelivery.shareddtoservice.dto.response;

import com.fooddelivery.shareddtoservice.model.OrderStatus;
import com.fooddelivery.shareddtoservice.dto.request.FoodItemRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderResponse {
    private Long id;
    private String customerName;
    private double totalPrice;
    private List<FoodItemResponse> foodItems;
    private OrderStatus status;
}