package com.example.KP_Book.controller;

import com.example.KP_Book.entity.User;
import com.example.KP_Book.repository.UserRepository;
import com.example.KP_Book.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserRepository userRepository;

    @GetMapping("/login")
    public String login(){
        return "login";
    }
    @GetMapping("/registration")
    public String registration(){
        return "registration";
    }
    @PostMapping("/registration")
    public String createUser(User user, Model model) throws IOException {
        if (!userService.saveUser(user)){
            model.addAttribute("errorMessage", "Пользователь с этим email: "+ user.getEmail() + " уже существует!");
            return "registration";
        }
        return "redirect:/login";
    }

}
