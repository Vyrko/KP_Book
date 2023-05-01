package com.example.KP_Book.repository;

import com.example.KP_Book.entity.Order;
import com.example.KP_Book.entity.User;
import org.aspectj.weaver.ast.Or;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Long> {
    List<Order> findAllByUser(User user);
}
