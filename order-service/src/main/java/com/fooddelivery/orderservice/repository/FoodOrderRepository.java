package com.fooddelivery.orderservice.repository;

import com.fooddelivery.orderservice.model.FoodOrder;
import com.fooddelivery.shareddtoservice.dto.response.OrderResponseList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodOrderRepository extends JpaRepository<FoodOrder, Long> {
    OrderResponseList findByDeletedFalse();
}
