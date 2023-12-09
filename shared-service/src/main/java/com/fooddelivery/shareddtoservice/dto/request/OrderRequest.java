package com.fooddelivery.shareddtoservice.dto.request;


import com.fooddelivery.shareddtoservice.model.PaymentMethod;
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
    private List<FoodItemRequest> foodItems;
    private PaymentMethod paymentMethod;
}
