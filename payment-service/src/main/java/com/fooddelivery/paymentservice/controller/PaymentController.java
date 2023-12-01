package com.fooddelivery.paymentservice.controller;

import com.fooddelivery.paymentservice.service.PaymentService;
import com.fooddelivery.shareddtoservice.dto.request.PaymentRequest;
import com.fooddelivery.shareddtoservice.dto.response.PaymentResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/payments")
public class PaymentController {
    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/process")
    public ResponseEntity<PaymentResponse> processPayment(@RequestBody PaymentRequest paymentRequest) {
        return ResponseEntity.ok(paymentService.processPayment(paymentRequest));
    }

    @PostMapping(path = "/refund", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> simulateRefund(@RequestBody Map<String, Long> requestBody) {
        Long orderId = requestBody.get("orderId");
        paymentService.simulateRefund(orderId);
        return ResponseEntity.ok().build();
    }
}

