package com.fooddelivery.foodservice.mapper;

import com.fooddelivery.foodservice.dto.request.FoodRequest;
import com.fooddelivery.foodservice.dto.response.FoodResponse;
import com.fooddelivery.foodservice.model.FoodItem;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class FoodMapper {
    public FoodItem requestToModel(FoodRequest foodRequest) {
        FoodItem item = new FoodItem();
        item.setName(foodRequest.getName());
        item.setPrice(foodRequest.getPrice());
        item.setQuantity(foodRequest.getQuantity());

        return item;
    }

    public FoodResponse modelToResponse(FoodItem foodItem) {
        FoodResponse response = new FoodResponse();
        response.setId(foodItem.getId());
        response.setName(foodItem.getName());
        response.setPrice(foodItem.getPrice());
        response.setQuantity(foodItem.getQuantity());

        return response;
    }
}
