package com.fooddelivery.paymentservice.service.impl;

import com.fooddelivery.paymentservice.dto.request.PaymentRequest;
import com.fooddelivery.paymentservice.dto.response.PaymentResponse;
import com.fooddelivery.paymentservice.mapper.PaymentMapper;
import com.fooddelivery.paymentservice.model.Payment;
import com.fooddelivery.paymentservice.model.PaymentMethod;
import com.fooddelivery.paymentservice.model.PaymentStatus;
import com.fooddelivery.paymentservice.repository.PaymentRepository;
import com.fooddelivery.paymentservice.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
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

    @Override
    public PaymentResponse processPayment(PaymentRequest paymentRequest) {
        if (paymentRequest.getPaymentMethod() == PaymentMethod.VISA_CARD && isPaymentSuccessful()) {
            Payment savedPayment = paymentRepository.save(paymentMapper.requestToModel(paymentRequest));
            return paymentMapper.modelToResponse(savedPayment);
        }
        else return new PaymentResponse(PaymentStatus.FAILED); // Simulated payment failure
    }

    private boolean isPaymentSuccessful() {
        // Simulate payment success with a 50% chance
        double successProbability = 0.50;
        return Math.random() < successProbability;
    }

}
