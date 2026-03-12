package com.codegym.springmvc.controllers;

import com.codegym.springmvc.models.User;
import com.codegym.springmvc.request.CreateUserRequest;
import com.codegym.springmvc.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {
    private UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    public String listUsers(Model model) {
        List<User> users = userService.getAllUsers();
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
        userService.deleteUserById(id);
        // chuyen huong /users
        return "redirect:/users";
    }

    @GetMapping("/create")
    public String createUser(Model model) {
        model.addAttribute("userRequest", new CreateUserRequest());
        return "users/create";
    }

    @PostMapping("/store")
    // su dung @ModelAttribute de lay data tu form phuc tap
    public String storeUser(@ModelAttribute("userRequest") CreateUserRequest createUserRequest) {
        // Xu ly logic khi submit form
        // Lay data tu request
        String username = createUserRequest.getUsername();
        String password = createUserRequest.getPassword();
        String email = createUserRequest.getEmail();
        User newUser = new User(username, password, email);
        // Luu nguoi dung moi vao database
        userService.createUser(newUser);
        // chuyen huong ve /users
        return "redirect:/users";
    }
}
