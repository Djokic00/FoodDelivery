package com.fooddelivery.shareddtoservice.model;

public enum PaymentStatus {
    SUCCESS("SUCCESS"),
    PENDING("PENDING"),
    FAIL("FAIL");

    private final String status;

    PaymentStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
