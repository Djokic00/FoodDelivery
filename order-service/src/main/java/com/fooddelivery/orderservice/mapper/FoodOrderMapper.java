package com.fooddelivery.orderservice.mapper;

import com.fooddelivery.orderservice.model.FoodItem;
import com.fooddelivery.orderservice.model.FoodOrder;
import com.fooddelivery.orderservice.model.FoodOrderItem;
import com.fooddelivery.shareddtoservice.dto.request.FoodItemRequest;
import com.fooddelivery.shareddtoservice.dto.request.OrderRequest;
import com.fooddelivery.shareddtoservice.dto.response.FoodItemResponse;
import com.fooddelivery.shareddtoservice.dto.response.OrderResponse;
import com.fooddelivery.shareddtoservice.model.OrderStatus;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class FoodOrderMapper {

    public FoodOrder requestToModel(OrderRequest orderRequest, double totalPrice, Map<String, FoodItem> existingFoodItemMap) {
        FoodOrder order = new FoodOrder();
        order.setCustomerName(orderRequest.getCustomerName());
        order.setTotalPrice(totalPrice);
        order.setPaymentMethod(orderRequest.getPaymentMethod());
        order.setStatus(OrderStatus.SUCCESS);

        List<FoodOrderItem> orderItems = mapFoodItemRequests(orderRequest.getFoodItems(), order, existingFoodItemMap);
        order.setFoodOrderItems(orderItems);

        return order;
    }

    public List<FoodOrderItem> mapFoodItemRequests(List<FoodItemRequest> foodItemRequests, FoodOrder foodOrder, Map<String, FoodItem> existingFoodItemMap) {
        return foodItemRequests.stream()
                .map(foodItemRequest -> {
                    FoodItem foodItem = existingFoodItemMap.get(foodItemRequest.getName());
                    int quantity = foodItemRequest.getQuantity();

                    // Create FoodOrderItem and set references
                    FoodOrderItem orderItem = new FoodOrderItem();
                    orderItem.setFoodOrder(foodOrder);
                    orderItem.setFoodItem(foodItem);
                    orderItem.setQuantity(quantity);

                    return orderItem;
                })
                .collect(Collectors.toList());
    }

    public OrderResponse modelToResponse(FoodOrder order) {
        OrderResponse response = new OrderResponse();
        response.setId(order.getId());
        response.setCustomerName(order.getCustomerName());
        response.setTotalPrice(order.getTotalPrice());
        response.setPaymentMethod(order.getPaymentMethod());
        response.setFoodItems(mapFoodOrderItemsToFoodItemResponses(order.getFoodOrderItems()));
        response.setStatus(order.getStatus());
        return response;
    }

    public List<FoodItemResponse> mapFoodOrderItemsToFoodItemResponses(List<FoodOrderItem> orderItems) {
        return orderItems.stream()
                .map(orderItem -> {
                    FoodItemResponse foodItemResponse = new FoodItemResponse();

                    // Set values based on FoodOrderItem
                    foodItemResponse.setId(orderItem.getFoodItem().getId());
                    foodItemResponse.setName(orderItem.getFoodItem().getName());
                    foodItemResponse.setPrice(orderItem.getFoodItem().getPrice());
                    foodItemResponse.setQuantity(orderItem.getQuantity());

                    return foodItemResponse;
                })
                .collect(Collectors.toList());
    }


}
