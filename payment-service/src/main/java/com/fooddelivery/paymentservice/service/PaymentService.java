package com.fooddelivery.paymentservice.service;


import com.fooddelivery.shareddtoservice.dto.request.PaymentRequest;
import com.fooddelivery.shareddtoservice.dto.response.PaymentResponse;

public interface PaymentService {
    PaymentResponse processPayment(PaymentRequest paymentRequest);
    void simulateRefund(Long orderId);
}
