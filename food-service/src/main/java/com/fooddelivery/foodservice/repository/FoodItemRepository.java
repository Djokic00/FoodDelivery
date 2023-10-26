package com.fooddelivery.foodservice.repository;

import com.fooddelivery.foodservice.model.FoodItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FoodItemRepository extends JpaRepository<FoodItem, Long> {
    Optional<FoodItem> findByNameAndQuantityGreaterThanEqual(String name, int quantity);
}

