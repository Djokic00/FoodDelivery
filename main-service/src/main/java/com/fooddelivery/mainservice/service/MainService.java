package com.fooddelivery.mainservice.service;
import com.fooddelivery.mainservice.exception.*;
import com.fooddelivery.shareddtoservice.dto.request.OrderRequest;
import com.fooddelivery.shareddtoservice.dto.request.PaymentRequest;
import com.fooddelivery.shareddtoservice.dto.response.OrderResponse;
import com.fooddelivery.shareddtoservice.dto.response.PaymentResponse;
import com.fooddelivery.shareddtoservice.model.OrderStatus;
import com.fooddelivery.shareddtoservice.model.PaymentMethod;
import com.fooddelivery.shareddtoservice.model.PaymentStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class MainService {
    private final OrderServiceClient orderServiceClient;
    private final PaymentServiceClient paymentServiceClient;
    private final SagaServiceClient sagaServiceClient;

    @Autowired
    public MainService(
            OrderServiceClient orderServiceClient,
            PaymentServiceClient paymentServiceClient,
            SagaServiceClient sagaServiceClient) {
        this.orderServiceClient = orderServiceClient;
        this.paymentServiceClient = paymentServiceClient;
        this.sagaServiceClient = sagaServiceClient;
    }

    @Transactional
    public OrderResponse createOrder(OrderRequest orderRequest) {
        checkFoodAvailability(orderRequest);

        ResponseEntity<OrderResponse> orderResponse = createOrderInternal(orderRequest);

        processPayment(orderResponse.getBody());

        return orderResponse.getBody();
    }

    private void checkFoodAvailability(OrderRequest orderRequest) {
        ResponseEntity<Boolean> isFoodAvailable = orderServiceClient.checkFoodAvailability(orderRequest);
        if (Boolean.FALSE.equals(isFoodAvailable.getBody())) {
            throw new NotFoundException("Food is not available");
        }
    }

    private ResponseEntity<OrderResponse> createOrderInternal(OrderRequest orderRequest) {
        ResponseEntity<OrderResponse> orderResponse = orderServiceClient.createOrder(orderRequest);

        if (orderResponse.getStatusCode().is4xxClientError()) {
            handleClientErrors(orderResponse);
        } else if (orderResponse.getStatusCode().is5xxServerError()) {
            handleServerErrors();
        }

        return orderResponse;
    }

    private void processPayment(OrderResponse orderResponse) {
        PaymentRequest paymentRequest = createPaymentRequest(orderResponse);
        ResponseEntity<PaymentResponse> paymentResponseEntity = paymentServiceClient.processPayment(paymentRequest);

        PaymentResponse paymentResponse = paymentResponseEntity.getBody();

        if (paymentResponse != null) {
            if (paymentResponse.getPaymentStatus() == PaymentStatus.FAIL) {
                handleFailedPayment(paymentResponse);
            } else if (paymentResponse.getPaymentStatus() == PaymentStatus.SUCCESS) {
                orderResponse.setStatus(OrderStatus.SUCCESS);
            }
        }
    }
    private void handleFailedPayment(PaymentResponse paymentResponse) {
        sagaServiceClient.initiateCompensation(paymentResponse.getOrderId());
        throw new FailedPaymentException("Payment not successful");
    }

    private PaymentRequest createPaymentRequest(OrderResponse orderResponse) {
        PaymentRequest paymentRequest = new PaymentRequest();
        paymentRequest.setOrderId(orderResponse.getId());
        paymentRequest.setAmount(orderResponse.getTotalPrice());
        paymentRequest.setPaymentMethod(orderResponse.getPaymentMethod());

        return paymentRequest;
    }

    private void handleClientErrors(ResponseEntity<OrderResponse> orderResponse) {
        if (orderResponse.getStatusCode() == HttpStatus.BAD_REQUEST) {
            throw new BadRequestException("Bad request");
        } else if (orderResponse.getStatusCode() == HttpStatus.FORBIDDEN) {
            throw new ForbiddenException("Forbidden");
        } else if (orderResponse.getStatusCode() == HttpStatus.NOT_FOUND) {
            throw new NotFoundException("Not found");
        }
    }

    private void handleServerErrors() {
        throw new InternalServerErrorException("Internal server error");
    }

}

