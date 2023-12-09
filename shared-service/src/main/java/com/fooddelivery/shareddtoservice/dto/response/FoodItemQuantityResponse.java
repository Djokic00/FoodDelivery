package com.fooddelivery.shareddtoservice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class FoodItemQuantityResponse {
    private Long foodItemId;
    private int quantity;
}
