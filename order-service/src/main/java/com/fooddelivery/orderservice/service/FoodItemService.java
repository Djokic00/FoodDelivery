package com.fooddelivery.orderservice.service;

import com.fooddelivery.orderservice.model.FoodItem;
import com.fooddelivery.shareddtoservice.dto.request.FoodItemRequest;
import com.fooddelivery.shareddtoservice.dto.request.OrderRequest;
import com.fooddelivery.shareddtoservice.dto.response.FoodItemQuantityResponse;
import com.fooddelivery.shareddtoservice.dto.response.FoodItemResponse;
import com.fooddelivery.shareddtoservice.dto.response.OrderResponse;

import java.util.List;

public interface FoodItemService {
    FoodItemResponse createFoodItem(FoodItemRequest foodItemRequest);
    List<FoodItem> getAllFoodItems();
    boolean checkFoodAvailability(OrderRequest orderRequest);
    List<FoodItemResponse> increaseFoodInventory(List<FoodItemQuantityResponse> foodItemQuantityResponseList);
    List<FoodItemResponse> reduceFoodInventory(List<FoodItemResponse> foodItemResponseList);

}