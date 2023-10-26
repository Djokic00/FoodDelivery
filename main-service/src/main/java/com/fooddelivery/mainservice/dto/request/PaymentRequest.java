package com.fooddelivery.mainservice.dto.request;

import com.fooddelivery.mainservice.model.PaymentMethod;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PaymentRequest {
    private double amount;
    private PaymentMethod paymentMethod;
}
