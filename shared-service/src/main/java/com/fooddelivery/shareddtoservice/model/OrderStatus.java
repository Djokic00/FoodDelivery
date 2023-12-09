package com.fooddelivery.shareddtoservice.model;

public enum OrderStatus {
    SUCCESS("SUCCESS"),
    PENDING("PENDING"),
    CANCELED_PAYMENT_NOT_SUCCESSFUL("PAYMENT_NOT_SUCCESSFUL");

    private final String status;

    OrderStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}