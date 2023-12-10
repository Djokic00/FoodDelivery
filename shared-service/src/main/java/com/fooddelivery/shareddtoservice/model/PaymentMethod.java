package com.fooddelivery.shareddtoservice.model;

public enum PaymentMethod {
    VISA("VISA"),
    PAYPAL("PAYPAL");

    private final String status;

    PaymentMethod(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
