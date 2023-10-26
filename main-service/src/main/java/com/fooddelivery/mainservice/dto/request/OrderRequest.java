package com.fooddelivery.mainservice.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderRequest {
    private String customerName;
    private double totalPrice;
    private List<FoodItemRequest> foodItems;
    private String status;
}
