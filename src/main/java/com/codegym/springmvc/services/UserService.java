package com.codegym.springmvc.services;

import com.codegym.springmvc.models.User;
import com.codegym.springmvc.repositories.IUserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private IUserRepository userRepository;

    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    public void createUser(User user) {
        userRepository.save(user);
    }
}
