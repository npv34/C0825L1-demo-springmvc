package com.codegym.springmvc.controllers.api;

import com.codegym.springmvc.models.User;
import com.codegym.springmvc.request.CreateUserRequest;
import com.codegym.springmvc.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserApiController {
    private final UserService userService;

    public UserApiController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping({"", "/"})
    public ResponseEntity<?> getAll() {
        try {
            List<User> users = userService.getAllUsers();
            return ResponseEntity.ok(users);
        }catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping()
    public ResponseEntity<?> createUser(@RequestBody CreateUserRequest userRequest) {
        try {
            userService.createUser(userRequest);
            return ResponseEntity.ok("Create user successfully");
        }catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") Long id) {
        try {
            // xoa nguoi dung theo id
            userService.deleteUserById(id);
            return ResponseEntity.ok("Delete user successfully");
        }catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable("id") Long id, @RequestBody CreateUserRequest userRequest) {
        try {
            userService.updateUserById(id, userRequest);
            return ResponseEntity.ok("Update user successfully");
        }catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/me")
    public ResponseEntity<?> getMyProfile() {
        try {
            User currentUser = userService.getCurrentUser();
            if (currentUser == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
            }
            return ResponseEntity.ok(currentUser);
        }catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
