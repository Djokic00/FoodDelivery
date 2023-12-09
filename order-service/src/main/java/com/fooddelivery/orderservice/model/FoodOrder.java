package com.fooddelivery.orderservice.model;

import com.fooddelivery.shareddtoservice.model.OrderStatus;
import com.fooddelivery.shareddtoservice.model.PaymentMethod;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "food_order")
public class FoodOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String customerName;
    private double totalPrice;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_status")
    private PaymentMethod paymentMethod;

    @OneToMany(mappedBy = "foodOrder", cascade = CascadeType.ALL)
    private List<FoodOrderItem> foodOrderItems;

    @Enumerated(EnumType.STRING)
    @Column(name = "order_status")
    private OrderStatus status;

    @Column(name = "deleted")
    private boolean deleted;
}


