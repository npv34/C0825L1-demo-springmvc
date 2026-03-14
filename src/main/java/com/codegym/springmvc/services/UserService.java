package com.codegym.springmvc.services;

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

    private IUserRepository userRepository;
    private IRoleRepository roleRepository;

    public UserService(IUserRepository userRepository, IRoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
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
        User user =  userRepository.findById(id).orElse(null);
        return new UpdateUserRequest(user.getId().intValue(),
                                        user.getUsername(),
                                        user.getEmail());
    }

    public void updateUser(Long id,
                           UpdateUserRequest updateUserRequest) {
        User user =  userRepository.findById(id).orElse(null);
        if (user != null) {
            user.setUsername(updateUserRequest.getUsername());
            user.setEmail(updateUserRequest.getEmail());
            userRepository.save(user);
        }
    }

}
