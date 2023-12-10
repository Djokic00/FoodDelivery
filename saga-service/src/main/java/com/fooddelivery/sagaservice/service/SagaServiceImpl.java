package com.fooddelivery.sagaservice.service;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class SagaServiceImpl implements SagaService {
//    private final KafkaTemplate<String, Map<String, Object>> kafkaTemplate;
    private final PaymentServiceClient paymentServiceClient;
    private final OrderServiceClient orderServiceClient;

    public SagaServiceImpl(KafkaTemplate<String, Map<String, Object>> kafkaTemplate, PaymentServiceClient paymentServiceClient, OrderServiceClient orderServiceClient) {
//        this.kafkaTemplate = kafkaTemplate;
        this.paymentServiceClient = paymentServiceClient;
        this.orderServiceClient = orderServiceClient;
    }

    public void initiateCompensation(Long orderId) {
        paymentServiceClient.simulateRefund(orderId);
        orderServiceClient.cancelOrder(orderId);
//        sendOrderCancellationMessage(orderId);
    }

//    private void sendOrderCancellationMessage(Long orderId) {
//        String topic = "order-cancellation";
//
//        Map<String, Object> messagePayload = new HashMap<>();
//        messagePayload.put("orderId", orderId.toString());
//        kafkaTemplate.send(topic, messagePayload);
//    }
}
