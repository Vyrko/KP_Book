package com.example.KP_Book.repository;

import com.example.KP_Book.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Long> {
}
