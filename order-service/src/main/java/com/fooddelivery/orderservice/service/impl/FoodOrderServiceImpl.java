package com.fooddelivery.orderservice.service.impl;

import com.fooddelivery.orderservice.exception.NotFoundException;
import com.fooddelivery.orderservice.mapper.FoodOrderMapper;
import com.fooddelivery.orderservice.model.FoodItem;
import com.fooddelivery.orderservice.model.FoodOrder;
import com.fooddelivery.orderservice.repository.FoodItemRepository;
import com.fooddelivery.orderservice.repository.FoodOrderRepository;
import com.fooddelivery.orderservice.service.FoodOrderService;
import com.fooddelivery.shareddtoservice.dto.request.FoodItemRequest;
import com.fooddelivery.shareddtoservice.dto.request.OrderRequest;
import com.fooddelivery.shareddtoservice.dto.response.OrderResponse;
import com.fooddelivery.shareddtoservice.dto.response.OrderResponseList;
import com.fooddelivery.shareddtoservice.model.OrderStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Service
public class FoodOrderServiceImpl implements FoodOrderService {
    private final FoodOrderRepository orderRepository;
    private final FoodOrderMapper orderMapper;

    private final FoodItemRepository foodItemRepository;

    public FoodOrderServiceImpl(FoodOrderRepository orderRepository, FoodOrderMapper orderMapper, FoodItemRepository foodItemRepository) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
        this.foodItemRepository = foodItemRepository;
    }

    @Override
    public OrderResponse createOrder(OrderRequest orderRequest) {
        List<String> foodItemNames = orderRequest.getFoodItems().stream()
                .map(FoodItemRequest::getName)
                .collect(Collectors.toList());

        List<FoodItem> existingFoodItems = foodItemRepository.findByNameInAndDeletedFalse(foodItemNames);
        Map<String, FoodItem> existingFoodItemMap = existingFoodItems.stream()
                .collect(Collectors.toMap(FoodItem::getName, Function.identity()));

        double totalPrice = calculateTotalPrice(orderRequest);
        System.out.println("Order REQUEST " + orderRequest.getFoodItems().get(0).getQuantity());
        FoodOrder order = orderRepository.save(orderMapper.requestToModel(orderRequest, totalPrice, existingFoodItemMap));
        System.out.println("Order " + order.getFoodItems().get(0).getQuantity());
        OrderResponse orderResponse = orderMapper.modelToResponse(order);
        return orderResponse;
    }

    private double calculateTotalPrice(OrderRequest orderRequest) {
        double totalPrice = 0;

        for (FoodItemRequest request : orderRequest.getFoodItems()) {
            Optional<FoodItem> foodItemOptional = foodItemRepository.findByNameAndDeletedFalse(request.getName());

            if (foodItemOptional.isPresent()) {
                FoodItem foodItem = foodItemOptional.get();
                totalPrice += request.getQuantity() * foodItem.getPrice();
            } else {
                throw new NotFoundException("Food item not found: " + request.getName());
            }
        }

        return totalPrice;
    }


    @Override
    public OrderResponseList getActiveOrders() {
        return orderRepository.findByDeletedFalse();
    }

    @KafkaListener(topics = "order-cancellation", groupId = "saga-group")
    @KafkaHandler
    public void processOrderCancellationEvent(@Payload Map<String, Object> messagePayload) {
        try {
            Long orderId = Long.parseLong((String) messagePayload.get("orderId"));
            cancelOrder(orderId);
        } catch (NumberFormatException | ClassCastException e) {
            System.out.println("Invalid orderId format or orderId is null.");
        }
    }

    @Override
    public void cancelOrder(Long orderId) {
        FoodOrder order = orderRepository.findById(orderId).orElseThrow(() -> new NotFoundException("Order with given ID is not found"));
        order.setDeleted(true);
        order.setStatus(OrderStatus.FAILED_PAYMENT_NOT_SUCCESSFUL);
        orderRepository.save(order);
        System.out.println("Cancelling order for order ID: " + orderId);
    }
}