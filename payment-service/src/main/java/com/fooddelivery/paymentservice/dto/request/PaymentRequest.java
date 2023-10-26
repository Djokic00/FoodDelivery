package com.fooddelivery.paymentservice.dto.request;

import com.fooddelivery.paymentservice.model.PaymentMethod;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentRequest {
    private double amount;
    private PaymentMethod paymentMethod;
}