package com.pharmacy.heavenly_healer_server.service.impl;

import com.pharmacy.heavenly_healer_server.model.User;
import com.pharmacy.heavenly_healer_server.repository.UserRepository;
import com.pharmacy.heavenly_healer_server.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getUserById(Integer userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with id: " + userId));
    }
}
