package com.example.spring_first_app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class IndexController {
    @GetMapping(value = "/login")
    public String loginpage() {
        return "login";
    }

    @GetMapping(value = "/home")
    public String homepage() {
        return "home";
    }
}
