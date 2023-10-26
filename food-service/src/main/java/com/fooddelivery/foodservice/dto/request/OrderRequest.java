package com.fooddelivery.foodservice.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderRequest {
    private String customerName;
    private double totalPrice;
    private List<FoodItemRequest> foodItems;
    private String status;
}
