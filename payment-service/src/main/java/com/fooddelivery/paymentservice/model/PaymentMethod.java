package com.fooddelivery.paymentservice.model;

public enum PaymentMethod {
        VISA_CARD("VISA_CARD"),
        PAYPAL("PAYPAL"),
        MASTER_CARD("FAILED");

        private final String status;

        PaymentMethod(String status) {
                this.status = status;
        }

        public String getStatus() {
                return status;
        }
}
