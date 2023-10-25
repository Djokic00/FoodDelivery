package com.fooddelivery.paymentservice.service;

import com.fooddelivery.paymentservice.dto.request.PaymentRequest;
import com.fooddelivery.paymentservice.dto.response.PaymentResponse;

public interface PaymentService {
    PaymentResponse processPayment(PaymentRequest paymentRequest);
}
