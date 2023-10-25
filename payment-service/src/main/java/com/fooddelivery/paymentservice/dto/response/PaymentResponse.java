package com.fooddelivery.paymentservice.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentResponse {
    private Long paymentId;
    private String paymentStatus;
    private double amount;
    private String paymentMethod;
}
