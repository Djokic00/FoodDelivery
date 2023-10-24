package com.fooddelivery.foodservice.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FoodResponse {
    private Long id;
    private String name;
    private double price;
    private int quantity;
}
