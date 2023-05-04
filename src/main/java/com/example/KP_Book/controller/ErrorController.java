package com.example.KP_Book.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequiredArgsConstructor
public class ErrorController {
    @RequestMapping(value = "/error", method = RequestMethod.GET)
    public String handleNotFound(Model model) {    model.addAttribute("status", HttpStatus.NOT_FOUND.value());
        return "error";}
}
