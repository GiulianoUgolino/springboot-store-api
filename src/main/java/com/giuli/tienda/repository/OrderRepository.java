package com.giuli.tienda.repository;

import com.giuli.tienda.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
