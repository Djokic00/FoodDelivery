package com.fooddelivery.shareddtoservice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FoodItemResponse {
    private Long id;
    private String name;
    private double price;
    private int quantity;

    public FoodItemResponse(Long id, int quantity) {
        this.id = id;
        this.quantity = quantity;
    }

    public FoodItemResponse(String name, double price) {
        this.name = name;
        this.price = price;
    }
}
