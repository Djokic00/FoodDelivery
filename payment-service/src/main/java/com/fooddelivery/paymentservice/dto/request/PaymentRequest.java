package com.fooddelivery.paymentservice.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentRequest {
    private double amount;
    private String paymentMethod;
}