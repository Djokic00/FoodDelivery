package com.fooddelivery.orderservice.service.impl;

import com.fooddelivery.orderservice.dto.request.FoodOrderRequest;
import com.fooddelivery.orderservice.dto.response.FoodOrderResponse;
import com.fooddelivery.orderservice.mapper.FoodOrderMapper;
import com.fooddelivery.orderservice.model.FoodOrder;
import com.fooddelivery.orderservice.repository.FoodOrderRepository;
import com.fooddelivery.orderservice.service.FoodOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class FoodOrderServiceImpl implements FoodOrderService {
    private final FoodOrderRepository orderRepository;
    private final FoodOrderMapper orderMapper;

    public FoodOrderServiceImpl(FoodOrderRepository orderRepository, FoodOrderMapper orderMapper) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
    }

    @Override
    public FoodOrderResponse createOrder(FoodOrderRequest orderRequest) {
        FoodOrder order = orderRepository.save(orderMapper.requestToModel(orderRequest));
        return orderMapper.modelToResponse(order);
    }
}