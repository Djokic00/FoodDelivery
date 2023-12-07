package com.fooddelivery.mainservice.service;
import com.fooddelivery.mainservice.exception.NotFoundException;
import com.fooddelivery.shareddtoservice.dto.request.OrderRequest;
import com.fooddelivery.shareddtoservice.dto.request.PaymentRequest;
import com.fooddelivery.shareddtoservice.dto.response.OrderResponse;
import com.fooddelivery.shareddtoservice.dto.response.PaymentResponse;
import com.fooddelivery.shareddtoservice.model.OrderStatus;
import com.fooddelivery.shareddtoservice.model.PaymentMethod;
import com.fooddelivery.shareddtoservice.model.PaymentStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
        // Step 1: Check if food is available
        ResponseEntity<Boolean> isFoodAvailable = orderServiceClient.checkFoodAvailability(orderRequest);
        if (Boolean.FALSE.equals(isFoodAvailable.getBody())) {
            throw new NotFoundException("Food is not available");
        }

        // Step 2: Create order
        ResponseEntity<OrderResponse> orderResponse = orderServiceClient.createOrder(orderRequest);

        // Step 3: Process payment and update the saga
        if (orderResponse.getBody() != null) {
            OrderResponse response = orderResponse.getBody();
            PaymentRequest paymentRequest = new PaymentRequest();
            paymentRequest.setOrderId(response.getId());
            paymentRequest.setAmount(response.getTotalPrice());
            paymentRequest.setPaymentMethod(PaymentMethod.VISA_CARD);
//            paymentRequest.setPaymentMethod(PaymentMethod.PAYPAL);

            ResponseEntity<PaymentResponse> paymentResponseEntity = paymentServiceClient.processPayment(paymentRequest);
            PaymentResponse paymentResponse = paymentResponseEntity.getBody();
            if (paymentResponse != null && paymentResponse.getPaymentStatus() == PaymentStatus.FAIL) {
                sagaServiceClient.initiateCompensation(paymentResponse.getOrderId());
                orderResponse.getBody().setStatus(OrderStatus.FAILED_PAYMENT_NOT_SUCCESSFUL);
                return orderResponse.getBody();
            }
            else if (paymentResponse != null && paymentResponse.getPaymentStatus() == PaymentStatus.SUCCESS) {
                orderResponse.getBody().setStatus(OrderStatus.SUCCESS);
            }
        }

//        if (orderResponse.getStatusCode().is4xxClientError()) {
//            // Handle client errors (e.g., bad request, unauthorized, not found)
//            // Extract error information from the response, if available
//        } else if (orderResponse.getStatusCode().is5xxServerError()) {
//
//        }

        return orderResponse.getBody();
    }
}

