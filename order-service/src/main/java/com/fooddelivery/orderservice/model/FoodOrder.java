package com.fooddelivery.orderservice.model;

import com.fooddelivery.shareddtoservice.model.OrderStatus;
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

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "food_order_items",
            joinColumns = { @JoinColumn(name = "food_order_id") },
            inverseJoinColumns = { @JoinColumn(name = "food_item_id") }
    )
    private List<FoodItem> foodItems;

    private double totalPrice;

    @Enumerated(EnumType.STRING)
    @Column(name = "order_status")
    private OrderStatus status;

    @Column(name = "deleted")
    private boolean deleted;
}


