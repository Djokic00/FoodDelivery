package com.fooddelivery.mainservice.dto.response;

import com.fooddelivery.mainservice.model.PaymentMethod;
import com.fooddelivery.mainservice.model.PaymentStatus;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PaymentResponse {
    private PaymentStatus paymentStatus;
    private double amount;
    private PaymentMethod paymentMethod;
}
