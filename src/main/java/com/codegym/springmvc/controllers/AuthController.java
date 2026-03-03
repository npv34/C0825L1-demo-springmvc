package com.codegym.springmvc.controllers;

import com.codegym.springmvc.request.LoginRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/auth")
public class AuthController {
    // Xu ly request "/auth/login" voi method GET
    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("loginRequest", new LoginRequest());
        return "auth/login";
    }

    // Xu ly request "/auth/login" voi method POST
    @PostMapping("/login")
    public String submitLogin(@ModelAttribute("loginRequest") LoginRequest loginRequest) {
        System.out.println("Username: " + loginRequest.getUsername());
        System.out.println("Password: " + loginRequest.getPassword());
        return "redirect:/home";
    }
}
