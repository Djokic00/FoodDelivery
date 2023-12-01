package com.fooddelivery.paymentservice.service.impl;

import com.fooddelivery.paymentservice.mapper.PaymentMapper;
import com.fooddelivery.paymentservice.model.Payment;
import com.fooddelivery.paymentservice.repository.PaymentRepository;
import com.fooddelivery.paymentservice.service.PaymentService;
import com.fooddelivery.shareddtoservice.dto.request.OrderRequest;
import com.fooddelivery.shareddtoservice.dto.request.PaymentRequest;
import com.fooddelivery.shareddtoservice.dto.response.PaymentResponse;
import com.fooddelivery.shareddtoservice.model.PaymentMethod;
import com.fooddelivery.shareddtoservice.model.PaymentStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;

    public PaymentServiceImpl(PaymentRepository paymentRepository, PaymentMapper paymentMapper) {
        this.paymentRepository = paymentRepository;
        this.paymentMapper = paymentMapper;
    }


    @KafkaHandler
    public PaymentResponse processPayment(PaymentRequest paymentRequest) {
        if (paymentRequest.getPaymentMethod() == PaymentMethod.VISA_CARD && isPaymentSuccessful()) {
            Payment savedPayment = paymentRepository.save(paymentMapper.requestToModel(paymentRequest));
            return paymentMapper.modelToResponse(savedPayment);
        } else {
            PaymentResponse paymentResponse = new PaymentResponse();
            paymentResponse.setOrderId(paymentRequest.getOrderId());
            paymentResponse.setPaymentStatus(PaymentStatus.FAIL);
            return paymentResponse;
        }
    }

    private boolean isPaymentSuccessful() {
        // Simulate payment success with a 50% chance
        double successProbability = 0.90;
        return Math.random() < successProbability;
    }

    public void simulateRefund(Long orderId) {
        System.out.println("Simulating refund for order ID: " + orderId);
    }

}
