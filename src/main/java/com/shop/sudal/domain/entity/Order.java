package com.shop.sudal.domain.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id")
    private Address address;

    @Column(nullable = false)
    private LocalDateTime orderDate;

    @Column(nullable = false)
    private String status;

    @Column(nullable = false)
    private Double deliveryPrice;

    @Column(nullable = false)
    private Double price;

    @Builder
    public Order(User user, Address address, LocalDateTime orderDateTime, String status, Double deliveryPrice, Double price) {
        if (user == null) throw new IllegalArgumentException("User cannot be null");
        if (address == null) throw new IllegalArgumentException("Address cannot be null");
        if (orderDateTime == null) throw new IllegalArgumentException("OrderDateTime cannot be null");
        if (status == null) throw new IllegalArgumentException("Status cannot be null");
        if (deliveryPrice == null) throw new IllegalArgumentException("DeliveryPrice cannot be null");
        if (price == null) throw new IllegalArgumentException("Price cannot be null");

        this.user = user;
        this.address = address;
        this.orderDate = orderDateTime;
        this.status = status;
        this.deliveryPrice = deliveryPrice;
        this.price = price;
    }
}
