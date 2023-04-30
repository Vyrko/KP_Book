package com.example.KP_Book.services;

import com.example.KP_Book.entity.Book;
import com.example.KP_Book.entity.Order;
import com.example.KP_Book.entity.User;
import com.example.KP_Book.repository.BookRepository;
import com.example.KP_Book.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {
    private final OrderRepository orderRepository;
    private final BookRepository bookRepository;
    public void createOrder(User user, Long id, int quantity) {
        Book book=bookRepository.findById(id).orElse(null);
        Order order=new Order();
        order.setUser(user);
        order.setQuantity(quantity);
        order.setBook(book);
        order.setPrice(book.getCost()*quantity);
        orderRepository.save(order);
    }
}
