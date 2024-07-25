package com.shop.sudal.domain.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderReturn {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @Column(nullable = false)
    private String returnType;

    @Column(nullable = false)
    private LocalDateTime returnDate;

    @Column(nullable = false)
    private String status;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    private String detail;
}
