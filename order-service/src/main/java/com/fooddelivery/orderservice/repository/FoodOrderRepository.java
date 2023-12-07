package com.fooddelivery.orderservice.repository;

import com.fooddelivery.orderservice.model.FoodOrder;
import com.fooddelivery.shareddtoservice.dto.response.FoodItemResponse;
import com.fooddelivery.shareddtoservice.dto.response.OrderResponse;
import com.fooddelivery.shareddtoservice.dto.response.OrderResponseList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoodOrderRepository extends JpaRepository<FoodOrder, Long> {
    OrderResponseList findByDeletedFalse();

    @Query("SELECT new com.fooddelivery.shareddtoservice.dto.response.OrderResponse(o.id, o.customerName, o.totalPrice, o.status) FROM FoodOrder o WHERE o.deleted = false")
    List<OrderResponse> findBasicOrderDetails();

    @Query("SELECT new com.fooddelivery.shareddtoservice.dto.response.FoodItemResponse(foi.foodItem.id, foi.quantity) FROM FoodOrderItem foi WHERE foi.foodOrder.id = :orderId")
    List<FoodItemResponse> findOrderItemsByOrderId(@Param("orderId") Long orderId);

}
