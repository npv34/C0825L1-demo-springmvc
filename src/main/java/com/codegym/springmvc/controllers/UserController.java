package com.codegym.springmvc.controllers;

import com.codegym.springmvc.models.User;
import com.codegym.springmvc.request.CreateUserRequest;
import com.codegym.springmvc.request.RoleRequest;
import com.codegym.springmvc.request.UpdateUserRequest;
import com.codegym.springmvc.services.UserService;
import com.codegym.springmvc.services.RoleService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {
    private UserService userService;
    private RoleService roleService;
    public UserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
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
        List<RoleRequest> roles = roleService.getAllRoles();
        model.addAttribute("roles", roles);
        model.addAttribute("userRequest", new CreateUserRequest());
        return "users/create";
    }

    @PostMapping("/create")
    // su dung @ModelAttribute de lay data tu form phuc tap
    public String storeUser(@Valid @ModelAttribute("userRequest") CreateUserRequest createUserRequest,
                            BindingResult bindingResult,
                            @RequestParam("roleId") Long roleId,
                            Model model) {
        // Xu ly logic khi submit form
        if (bindingResult.hasErrors()){
            System.out.println("Validation errors: " + bindingResult.getAllErrors());
            List<RoleRequest> roles = roleService.getAllRoles();
            model.addAttribute("roles", roles);
            model.addAttribute("userRequest", createUserRequest);
            return "users/create";
        }
        // Luu nguoi dung moi vao database
        createUserRequest.setRoleId(roleId);
        userService.createUser(createUserRequest);
        // chuyen huong ve /users
        return "redirect:/users";
    }

    @GetMapping("/{id}/edit")
    public String editUser(@PathVariable("id") Long id, Model model) {
        // Lay thong tin nguoi dung theo id
        UpdateUserRequest userEdit = userService.getUserById(id);
        if (userEdit == null) {
            // Xu ly khi khong tim thay nguoi dung
            return "error/404";
        }
        // Dua thong tin nguoi dung vao model de hien thi tren form
        model.addAttribute("userEdit", userEdit);
        List<RoleRequest> roles = roleService.getAllRoles();
        model.addAttribute("roles", roles);
        // chuyen huong /users
        return "users/edit";
    }

    @PostMapping("/{id}/edit")
    public String updateUser(@Valid
                             @ModelAttribute("userEdit") UpdateUserRequest userEdit,
                             BindingResult bindingResult,
                             @PathVariable("id") Long id,
                             Model model) {
        // Xu ly logic cap nhat thong tin nguoi dung
        if (bindingResult.hasErrors()){
            // Dua thong tin nguoi dung vao model de hien thi tren form
            model.addAttribute("userEdit", userEdit);
            List<RoleRequest> roles = roleService.getAllRoles();
            model.addAttribute("roles", roles);
            return "users/edit";
        }
        // Luu thong tin nguoi dung da cap nhat vao database
        userService.updateUser(id, userEdit);
        // chuyen huong ve /users
        return "redirect:/users";

    }
}
