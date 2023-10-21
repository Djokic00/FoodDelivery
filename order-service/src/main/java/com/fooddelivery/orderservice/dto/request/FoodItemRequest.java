package com.fooddelivery.orderservice.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FoodItemRequest {
    private String name;
    private double price;
    private int quantity;
}
