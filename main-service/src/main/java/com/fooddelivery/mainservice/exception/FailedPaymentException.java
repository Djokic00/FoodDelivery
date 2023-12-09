package com.fooddelivery.mainservice.exception;

import org.springframework.http.HttpStatus;

public class FailedPaymentException extends CustomException {
        public FailedPaymentException(String message) {
            super(message, ErrorCode.FAILED_PAYMENT, HttpStatus.INTERNAL_SERVER_ERROR);
        }

}
