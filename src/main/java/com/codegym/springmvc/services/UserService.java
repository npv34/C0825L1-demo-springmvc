package com.codegym.springmvc.services;

import com.codegym.springmvc.exceptions.ResourceNotFoundException;
import com.codegym.springmvc.models.Role;
import com.codegym.springmvc.models.User;
import com.codegym.springmvc.repositories.IRoleRepository;
import com.codegym.springmvc.repositories.IUserRepository;
import com.codegym.springmvc.request.CreateUserRequest;
import com.codegym.springmvc.request.UpdateUserRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final IUserRepository userRepository;
    private final IRoleRepository roleRepository;

    public UserService(IUserRepository userRepository, IRoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void deleteUserById(Long id) {
        // kiem tra xem user co id ton tai hay k
        User user =  userRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("User not found with id: " + id));
        userRepository.delete(user);
    }

    public void createUser(CreateUserRequest createUserRequest) {
        // Lay data tu request
        String username = createUserRequest.getUsername();
        String password = createUserRequest.getPassword();
        String email = createUserRequest.getEmail();
        User newUser = new User(username, password, email);
        // set role cho user
        Long roleId = createUserRequest.getRoleId();
        Role role = roleRepository.findById(roleId).orElse(null);
        newUser.setRole(role);

        userRepository.save(newUser);
    }

    public UpdateUserRequest getUserById(Long id) {
        User user =  userRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("User not found with id: " + id));
        return new UpdateUserRequest(user.getId().intValue(),
                                        user.getUsername(),
                                        user.getEmail(), user.getRole() != null ? user.getRole().getId() : null);
    }

    public void updateUser(Long id,
                           UpdateUserRequest updateUserRequest) {
        User user =  userRepository.findById(id).orElse(null);
        if (user != null) {
            user.setUsername(updateUserRequest.getUsername());
            user.setEmail(updateUserRequest.getEmail());
            // set role cho user
            Long roleId = updateUserRequest.getRoleId();
            Role role = roleRepository.findById(roleId).orElse(null);
            if (role != null) {
                user.setRole(role);
            }
            userRepository.save(user);
        }
    }

}
