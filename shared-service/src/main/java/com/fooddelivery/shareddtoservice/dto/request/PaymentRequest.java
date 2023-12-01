package com.fooddelivery.shareddtoservice.dto.request;

import com.fooddelivery.shareddtoservice.model.PaymentMethod;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PaymentRequest {
    private Long orderId;
    private double amount;
    private PaymentMethod paymentMethod;
}
