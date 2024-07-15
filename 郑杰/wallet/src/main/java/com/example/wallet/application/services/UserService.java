package com.example.wallet.application.services;

// UserService.java - 应用服务
import com.example.wallet.domain.models.User;
import com.example.wallet.infrastructure.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User user) {
        // Implement business logic such as password encryption before saving
        user.setStatus("active");
        return userRepository.save(user);
    }

    // Add other business methods as needed
}