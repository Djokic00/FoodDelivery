package com.fooddelivery.paymentservice.model;

public enum PaymentStatus {
    SUCCESS("SUCCESS"),
    PENDING("PENDING"),
    FAILED("FAILED");

    private final String status;

    PaymentStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
