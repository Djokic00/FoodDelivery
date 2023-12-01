package com.fooddelivery.sagaservice.service;

public interface SagaService {
    void initiateCompensation(Long orderId);
}
