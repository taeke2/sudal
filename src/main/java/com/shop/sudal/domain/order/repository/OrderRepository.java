package com.shop.sudal.domain.order.repository;

import com.shop.sudal.domain.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
