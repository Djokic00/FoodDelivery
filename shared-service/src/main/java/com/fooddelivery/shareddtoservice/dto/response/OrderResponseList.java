package com.fooddelivery.shareddtoservice.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderResponseList {
    private List<OrderResponse> orderResponseList;
}
