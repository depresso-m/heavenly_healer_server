package com.pharmacy.heavenly_healer_server.service;

import com.pharmacy.heavenly_healer_server.util.MyUserDetails;
import com.pharmacy.heavenly_healer_server.model.User;
import com.pharmacy.heavenly_healer_server.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

// Отвечает ТОЛЬКО за аутентификацию и загрузку пользователя для Spring Security.

@Service
public class MyUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    public MyUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByEmail(email); // Так как у меня авторизация по email, надо возвращать email
        return user.map(MyUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException(email + " not found"));
    }
}
