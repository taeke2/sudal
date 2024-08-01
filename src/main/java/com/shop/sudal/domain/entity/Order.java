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
    @JoinColumn(name = "member_id")
    private Member member;

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
    public Order(Long id, Member member, Address address, LocalDateTime orderDateTime, String status, Double deliveryPrice, Double price) {
        if (member == null) throw new IllegalArgumentException("Member cannot be null");
        if (address == null) throw new IllegalArgumentException("Address cannot be null");
        if (orderDateTime == null) throw new IllegalArgumentException("OrderDateTime cannot be null");
        if (status == null) throw new IllegalArgumentException("Status cannot be null");
        if (deliveryPrice == null) throw new IllegalArgumentException("DeliveryPrice cannot be null");
        if (price == null) throw new IllegalArgumentException("Price cannot be null");

        this.id = id;
        this.member = member;
        this.address = address;
        this.orderDate = orderDateTime;
        this.status = status;
        this.deliveryPrice = deliveryPrice;
        this.price = price;
    }
}
