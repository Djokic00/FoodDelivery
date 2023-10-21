package com.fooddelivery.orderservice.mapper;

import com.fooddelivery.orderservice.dto.request.FoodItemRequest;
import com.fooddelivery.orderservice.dto.request.FoodOrderRequest;
import com.fooddelivery.orderservice.dto.response.FoodOrderResponse;
import com.fooddelivery.orderservice.model.FoodItem;
import com.fooddelivery.orderservice.model.FoodOrder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class FoodOrderMapper {
    public FoodOrder requestToModel(FoodOrderRequest orderRequest) {
        FoodOrder order = new FoodOrder();
        order.setCustomerName(orderRequest.getCustomerName());
        order.setTotalPrice(orderRequest.getTotalPrice());
        order.setStatus(orderRequest.getStatus());
        order.setFoodItems(mapFoodItemRequests(orderRequest.getFoodItems(), order));

        return order;
    }

    public List<FoodItem> mapFoodItemRequests(List<FoodItemRequest> foodItemRequests, FoodOrder foodOrder) {
        return foodItemRequests.stream()
                .map(foodItemRequest -> {
                    FoodItem foodItem = new FoodItem(
                            foodItemRequest.getName(),
                            foodItemRequest.getPrice(),
                            foodItemRequest.getQuantity()
                    );
                    foodItem.setFoodOrder(foodOrder); // Set the association to the parent FoodOrder
                    return foodItem;
                })
                .collect(Collectors.toList());
    }


    public FoodOrderResponse modelToResponse(FoodOrder order) {
        FoodOrderResponse response = new FoodOrderResponse();
        response.setId(order.getId());
        response.setStatus(order.getStatus());

        return response;
    }

}
