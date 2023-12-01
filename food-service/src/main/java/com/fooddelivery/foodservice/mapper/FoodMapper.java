package com.fooddelivery.foodservice.mapper;

import com.fooddelivery.foodservice.model.FoodItem;
import com.fooddelivery.shareddtoservice.dto.request.FoodItemRequest;
import com.fooddelivery.shareddtoservice.dto.response.FoodItemResponse;
import org.springframework.stereotype.Component;

@Component
public class FoodMapper {
    public FoodItem requestToModel(FoodItemRequest foodItemRequest) {
        FoodItem item = new FoodItem();
        item.setName(foodItemRequest.getName());
//        item.setPrice(foodItemRequest.getPrice());
        item.setQuantity(foodItemRequest.getQuantity());

        return item;
    }

    public FoodItemResponse modelToResponse(FoodItem foodItem) {
        FoodItemResponse response = new FoodItemResponse();
        response.setId(foodItem.getId());
        response.setName(foodItem.getName());
        response.setPrice(foodItem.getPrice());
        response.setQuantity(foodItem.getQuantity());

        return response;
    }
}
