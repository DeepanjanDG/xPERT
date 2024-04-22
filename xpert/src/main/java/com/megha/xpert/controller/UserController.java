package com.megha.xpert.controller;

import com.megha.xpert.model.User;
import com.megha.xpert.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/index")
    public String home() {
        return "index";
    }

    @GetMapping("/register")
    public String showRegistrationForm() {
        return "register";
    }

    @GetMapping("/login")
    public String loginuser() {
        return "login";
    }

    @PostMapping("/register")
    public String registerUser(User user) {
        userService.registerUser(user);
        return "redirect:/register";

    }

    @PostMapping("/login")
    public String loginUser(User user) {
        boolean isValidUser = userService.validateUser(user);
        if (isValidUser) {
            return "redirect:/index"; // Redirect to home page if the user is valid
        } else {
            return "redirect:/login"; // Redirect back to login page if the user is not valid
        }
    }
}



