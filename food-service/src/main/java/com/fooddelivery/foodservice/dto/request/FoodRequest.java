package com.fooddelivery.foodservice.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FoodRequest {
    private String name;
    private double price;
    private int quantity;
}
