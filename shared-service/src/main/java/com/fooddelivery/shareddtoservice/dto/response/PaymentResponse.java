package com.fooddelivery.shareddtoservice.dto.response;

import com.fooddelivery.shareddtoservice.model.PaymentMethod;
import com.fooddelivery.shareddtoservice.model.PaymentStatus;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PaymentResponse {
    private Long orderId;
    private PaymentStatus paymentStatus;
    private double amount;
    private PaymentMethod paymentMethod;
}