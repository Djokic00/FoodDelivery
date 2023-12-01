package com.fooddelivery.paymentservice.mapper;

import com.fooddelivery.paymentservice.model.Payment;
import com.fooddelivery.shareddtoservice.dto.request.PaymentRequest;
import com.fooddelivery.shareddtoservice.dto.response.PaymentResponse;
import com.fooddelivery.shareddtoservice.model.PaymentStatus;
import org.springframework.stereotype.Component;

@Component
public class PaymentMapper {
    public Payment requestToModel(PaymentRequest paymentRequest) {
        Payment payment = new Payment();
        payment.setPaymentStatus(PaymentStatus.SUCCESS);
        payment.setAmount(paymentRequest.getAmount());
        payment.setPaymentMethod(paymentRequest.getPaymentMethod());

        return payment;
    }

    public PaymentResponse modelToResponse(Payment savedPayment) {
        PaymentResponse paymentResponse = new PaymentResponse();
        paymentResponse.setOrderId(savedPayment.getOrderId());
        paymentResponse.setPaymentStatus(savedPayment.getPaymentStatus());
        paymentResponse.setAmount(savedPayment.getAmount());
        paymentResponse.setPaymentMethod(savedPayment.getPaymentMethod());

        return paymentResponse;
    }
}
