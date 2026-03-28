package com.codegym.springmvc.controllers.api;

import com.codegym.springmvc.models.User;
import com.codegym.springmvc.request.CreateUserRequest;
import com.codegym.springmvc.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/api/v1/users")
public class UserApiController {
    private final UserService userService;

    public UserApiController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping({"", "/"})
    public ResponseEntity<?> getAll() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @PostMapping()
    public ResponseEntity<?> createUser(@RequestBody CreateUserRequest userRequest) {
        userService.createUser(userRequest);
        return ResponseEntity.ok("Create user successfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") Long id) {
        try {
            // xoa nguoi dung theo id
            userService.deleteUserById(id);
            // chuyen huong /users
            return ResponseEntity.ok("Delete user successfully");
        }catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
