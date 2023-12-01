package com.fooddelivery.orderservice.mapper;

import com.fooddelivery.orderservice.model.FoodItem;
import com.fooddelivery.orderservice.model.FoodOrder;
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
        order.setFoodItems(mapFoodItemRequests(orderRequest.getFoodItems(), order, existingFoodItemMap));
        order.setStatus(OrderStatus.SUCCESS);

        return order;
    }

    public List<FoodItem> mapFoodItemRequests(List<FoodItemRequest> foodItemRequests, FoodOrder foodOrder, Map<String, FoodItem> existingFoodItemMap) {
        return foodItemRequests.stream()
                .map(foodItemRequest -> {
                    FoodItem foodItem = existingFoodItemMap.get(foodItemRequest.getName());
                    foodItem.getFoodOrders().add(foodOrder);
                    return foodItem;
                })
                .collect(Collectors.toList());
    }


    public OrderResponse modelToResponse(FoodOrder order) {
        OrderResponse response = new OrderResponse();
        response.setId(order.getId());
        response.setCustomerName(order.getCustomerName());
        response.setTotalPrice(order.getTotalPrice());
        response.setFoodItems(mapFoodItemToFoodItemResponse(order.getFoodItems()));
        response.setStatus(order.getStatus());

        return response;
    }

    public List<FoodItemResponse> mapFoodItemToFoodItemResponse(List<FoodItem> foodItems) {
        return foodItems.stream()
                .map(foodItem -> {
                    FoodItemResponse foodItemResponse = new FoodItemResponse(
                            foodItem.getId(),
                            foodItem.getName(),
                            foodItem.getPrice(),
                            foodItem.getQuantity()
                    );
                    return foodItemResponse;
                })
                .collect(Collectors.toList());
    }

}
