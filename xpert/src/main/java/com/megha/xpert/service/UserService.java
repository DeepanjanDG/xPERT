package com.megha.xpert.service;

import com.megha.xpert.model.User;
import com.megha.xpert.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.getAllUsers();
    }

    public void registerUser(User user) {
        userRepository.save(user);
    }

    public boolean validateUser(User user) {
        return userRepository.validateUser(user);
    }

    // Other service methods can be added here
}


