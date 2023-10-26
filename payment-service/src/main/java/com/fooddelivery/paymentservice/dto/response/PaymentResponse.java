package com.fooddelivery.paymentservice.dto.response;

import com.fooddelivery.paymentservice.model.PaymentMethod;
import com.fooddelivery.paymentservice.model.PaymentStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PaymentResponse {
    private Long paymentId;
    private PaymentStatus paymentStatus;
    private double amount;
    private PaymentMethod paymentMethod;

    public PaymentResponse(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
}
