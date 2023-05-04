package com.example.KP_Book.controller;

import com.example.KP_Book.entity.Order;
import com.example.KP_Book.entity.User;
import com.example.KP_Book.repository.UserRepository;
import com.example.KP_Book.services.OrderService;
import com.example.KP_Book.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.io.IOException;
import java.net.BindException;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    private final OrderService orderService;
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/registration")
    public String registration(@ModelAttribute("user") User user, Model model) {
        model.addAttribute("user", user);
        return "registration";
    }

    @PostMapping("/registration")
    public String createUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult, Model model) throws IOException {
        if (bindingResult.hasErrors()) return "registration";
        if (userService.emailExist(user.getEmail())){
            model.addAttribute("error", "Такой пользователь уже существует");
            model.addAttribute("user", user);
            return "registration";
        }
        userService.saveUser(user);
        return "redirect:/login";
    }
    @PreAuthorize("hasAnyAuthority('ROLE_USER')")
    @GetMapping ("/user")
    public String userInfo(
            @ModelAttribute("newUser") @Valid User newUser, BindingResult bindingResult,
            @AuthenticationPrincipal User user, Model model){
        List<Order> orders=orderService.readAllOrdersByUserId(user);
        model.addAttribute("user", user);
        model.addAttribute("orders", orders);
        return "userInfo";
    }
    @PreAuthorize("hasAnyAuthority('ROLE_USER')")
    @PostMapping("/user/update/{id}")
    public String userUpdate(
            @PathVariable("id") Long id, User user, Model model){
        userService.updateUser(user,id);
        return "userInfo";
    }
}
