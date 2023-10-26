package com.fooddelivery.mainservice.service;


import com.fooddelivery.mainservice.dto.request.OrderRequest;
import com.fooddelivery.mainservice.dto.request.PaymentRequest;
import com.fooddelivery.mainservice.dto.response.OrderResponse;
import com.fooddelivery.mainservice.dto.response.PaymentResponse;
import com.fooddelivery.mainservice.model.PaymentMethod;
import com.fooddelivery.mainservice.model.PaymentStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MainService {
    private final OrderServiceClient orderServiceClient;
    private final FoodServiceClient foodServiceClient;
    private final PaymentServiceClient paymentServiceClient;
//    private final SagaServiceClient sagaServiceClient;

    @Autowired
    public MainService(
            OrderServiceClient orderServiceClient,
            FoodServiceClient foodServiceClient,
            PaymentServiceClient paymentServiceClient
//            SagaServiceClient sagaServiceClient
    ) {
        this.orderServiceClient = orderServiceClient;
        this.foodServiceClient = foodServiceClient;
        this.paymentServiceClient = paymentServiceClient;
//        this.sagaServiceClient = sagaServiceClient;
    }

    @Transactional
    public OrderResponse createOrder(OrderRequest orderRequest) {
        // Step 1: Check if food is available
        ResponseEntity<Boolean> isFoodAvailable = foodServiceClient.checkFoodAvailability(orderRequest);
        if (Boolean.FALSE.equals(isFoodAvailable.getBody())) {
            OrderResponse orderResponse = new OrderResponse();
            orderResponse.setStatus("Food not available");
            return orderResponse;
        }

        // Step 2: Create order
        ResponseEntity<OrderResponse> orderResponse = orderServiceClient.createOrder(orderRequest);

        // Step 3: Process payment and update the saga
        PaymentRequest paymentRequest = new PaymentRequest();
        paymentRequest.setAmount(orderRequest.getTotalPrice());
        paymentRequest.setPaymentMethod(PaymentMethod.VISA_CARD);
//        paymentRequest.setPaymentMethod(PaymentMethod.PAYPAL);

        ResponseEntity<PaymentResponse> paymentStatus = paymentServiceClient.processPayment(paymentRequest);
        PaymentResponse paymentResponse = paymentStatus.getBody();
        if (paymentResponse == null || paymentResponse.getPaymentStatus() == PaymentStatus.FAILED) {
            //sagaServiceClient.compensatePaymentService(orderResponse.getOrderId());
            return orderResponse.getBody();
        }

        return orderResponse.getBody();
    }
}

