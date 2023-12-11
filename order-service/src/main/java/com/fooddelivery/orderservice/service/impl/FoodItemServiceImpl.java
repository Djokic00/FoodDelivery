package com.fooddelivery.orderservice.service.impl;

import com.fooddelivery.orderservice.exception.NotFoundException;
import com.fooddelivery.orderservice.mapper.FoodItemMapper;
import com.fooddelivery.orderservice.model.FoodItem;
import com.fooddelivery.orderservice.repository.FoodItemRepository;
import com.fooddelivery.orderservice.service.FoodItemService;
import com.fooddelivery.shareddtoservice.dto.request.FoodItemRequest;
import com.fooddelivery.shareddtoservice.dto.request.OrderRequest;
import com.fooddelivery.shareddtoservice.dto.response.FoodItemQuantityResponse;
import com.fooddelivery.shareddtoservice.dto.response.FoodItemResponse;
import com.fooddelivery.shareddtoservice.dto.response.OrderResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FoodItemServiceImpl implements FoodItemService {
    private final FoodItemRepository foodItemRepository;
    private final FoodItemMapper foodMapper;

    public FoodItemServiceImpl(FoodItemRepository foodItemRepository, FoodItemMapper foodItemMapper) {
        this.foodItemRepository = foodItemRepository;
        this.foodMapper = foodItemMapper;
    }

    @Override
    public FoodItemResponse createFoodItem(FoodItemRequest foodItemRequest) {
        FoodItem foodItem = foodItemRepository.save(foodMapper.requestToModel(foodItemRequest));
        return foodMapper.modelToResponse(foodItem);
    }

    public List<FoodItem> getAllFoodItems() {
        return foodItemRepository.findAll();
    }

    public boolean checkFoodAvailability(OrderRequest orderRequest) {
        List<FoodItemRequest> foodItems = orderRequest.getFoodItems();

        for (FoodItemRequest foodItemRequest : foodItems) {
            String name = foodItemRequest.getName();
            int quantity = foodItemRequest.getQuantity();

            if (!checkSingleFoodItemAvailability(name, quantity)) {
                return false;
            }
        }
        return true;
    }

    public boolean checkSingleFoodItemAvailability(String name, int quantity) {
        Optional<FoodItem> foodItem = foodItemRepository.findByNameAndQuantityGreaterThanEqual(name, quantity);
        if (foodItem.isEmpty()) {
            return false;
        }
        return true;
    }

    @Override
    public List<FoodItemResponse> increaseFoodInventory(List<FoodItemQuantityResponse> foodItemQuantityResponseList) {
        List<FoodItemResponse> foodItemResponseList = new ArrayList<>();
        for (FoodItemQuantityResponse foodItemQuantityResponse: foodItemQuantityResponseList) {
            FoodItem foodItem = foodItemRepository.findById(foodItemQuantityResponse.getFoodItemId()).orElseThrow(() -> {
                throw new NotFoundException("Food item with given id does not exists!");
            });
            if (foodItem != null) {
                int newQuantity = foodItem.getQuantity() + foodItemQuantityResponse.getQuantity();
                foodItem.setQuantity(newQuantity);
                foodItemRepository.save(foodItem);
                foodItemResponseList.add(foodMapper.modelToResponse(foodItem));
            }
        }
        return foodItemResponseList;
    }

    @Override
    public List<FoodItemResponse> reduceFoodInventory(List<FoodItemResponse> foodItemResponseList) {
        List<FoodItemResponse> updatedFoodItems = new ArrayList<>();

        for (FoodItemResponse foodItemResponse : foodItemResponseList) {
            Optional<FoodItem> optionalFoodItem = foodItemRepository.findByName(foodItemResponse.getName());
            if (optionalFoodItem.isPresent()) {
                FoodItem foodItem = optionalFoodItem.get();
                int currentQuantity = foodItem.getQuantity();
                if (currentQuantity >= foodItemResponse.getQuantity()) {
                    foodItem.setQuantity(currentQuantity - foodItemResponse.getQuantity());
                    foodItemRepository.save(foodItem);
                    updatedFoodItems.add(foodMapper.modelToResponse(foodItem));
                }
            }
        }
        return updatedFoodItems;
    }
}
