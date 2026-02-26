package com.codegym.springmvc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/auth")
public class AuthController {
    // Xu ly request "/auth/login" voi method GET
    @GetMapping("/login")
    public String login() {
        return "auth/login";
    }

    // Xu ly request "/auth/login" voi method POST
    @PostMapping("/login")
    public String submitLogin(@RequestParam("username") String username,
                              @RequestParam("password") String password) {
        System.out.println("Username: " + username);
        System.out.println("Password: " + password);
        return "redirect:/home";
    }
}
