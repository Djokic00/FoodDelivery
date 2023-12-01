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
}
