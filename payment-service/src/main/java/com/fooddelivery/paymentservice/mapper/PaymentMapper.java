package com.fooddelivery.paymentservice.mapper;

import com.fooddelivery.paymentservice.dto.request.PaymentRequest;
import com.fooddelivery.paymentservice.dto.response.PaymentResponse;
import com.fooddelivery.paymentservice.model.Payment;
import org.springframework.stereotype.Component;

@Component
public class PaymentMapper {
    public Payment requestToModel(PaymentRequest paymentRequest) {
        Payment payment = new Payment();
        payment.setPaymentStatus("Success");
        payment.setAmount(paymentRequest.getAmount());
        payment.setPaymentMethod(paymentRequest.getPaymentMethod());

        return payment;
    }

    public PaymentResponse modelToResponse(Payment savedPayment) {
        PaymentResponse paymentResponse = new PaymentResponse();
        paymentResponse.setPaymentId(savedPayment.getPaymentId());
        paymentResponse.setPaymentStatus(savedPayment.getPaymentStatus());
        paymentResponse.setAmount(savedPayment.getAmount());
        paymentResponse.setPaymentMethod(savedPayment.getPaymentMethod());

        return paymentResponse;
    }
}
