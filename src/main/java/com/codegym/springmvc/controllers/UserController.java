package com.codegym.springmvc.controllers;

import com.codegym.springmvc.entities.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    private List<User> users = new ArrayList<>();

    public UserController() {
        users.add(new User(1, "john_doe", "password123", "john@gmail.com"));
        users.add(new User(2, "jane_doe", "password456", "jane@gmail.com"));
    }

    @GetMapping("")
    public String listUsers(Model model) {
        model.addAttribute("users", users);
        return "users/list";
    }

    @GetMapping("/{id}/profile")
    public String userProfile(@PathVariable("id") Long id,
                              Model model) {
        System.out.println("User ID: " + id);

        // dua data vao model
        model.addAttribute("userId", id);
        return "users/profile";
    }

    @GetMapping("/{id}/delete")
    public String deleteUser(@PathVariable("id") Long id) {
        // xoa nguoi dung theo id
        users.removeIf(user -> user.getId() == id);
        // chuyen huong /users
        return "redirect:/users";
    }

    @GetMapping("/create")
    public String createUser() {
        return "users/create";
    }

    @PostMapping("/store")
    // su dung @ModelAttribute de lay data tu form phuc tap
    public String storeUser(@RequestParam("username") String username,
                            @RequestParam("password") String password,
                            @RequestParam("email") String email) {
        // Xu ly logic khi submit form
        // Lay data tu request
        // Them moi vao users
        int newId = users.size() + 1;
        User newUser = new User(newId, username, password, email);
        users.add(newUser);
        // chuyen huong ve /users
        return "redirect:/users";
    }
}
