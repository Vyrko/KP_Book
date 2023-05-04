package com.example.KP_Book.controller;

import com.example.KP_Book.entity.Order;
import com.example.KP_Book.entity.User;
import com.example.KP_Book.repository.BookRepository;
import com.example.KP_Book.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/buyBook/{id}")
    public String byuBook(Model model, @AuthenticationPrincipal User user,
                                       @PathVariable Long id,
                                       @RequestParam("quantity") int quantity){
        orderService.createOrder(user, id, quantity);
        List<Order> orders=orderService.readAllOrdersByUserId(user);
        model.addAttribute("user", user);
        model.addAttribute("orders", orders);
        return "userInfo";
    }
}
