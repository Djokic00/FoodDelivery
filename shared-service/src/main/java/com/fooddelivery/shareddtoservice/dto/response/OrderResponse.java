package com.fooddelivery.shareddtoservice.dto.response;

import com.fooddelivery.shareddtoservice.model.OrderStatus;
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
public class OrderResponse {
    private Long id;
    private String customerName;
    private double totalPrice;
    private List<FoodItemResponse> foodItems;
    private OrderStatus status;
    private PaymentMethod paymentMethod;

    public OrderResponse(Long id, String customerName, double totalPrice, OrderStatus status) {
        this.id = id;
        this.customerName = customerName;
        this.totalPrice = totalPrice;
        this.status = status;
    }
}