package com.example.KP_Book.services;

import com.example.KP_Book.entity.Enum.Role;
import com.example.KP_Book.entity.User;
import com.example.KP_Book.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public boolean saveUser(User user) throws IOException {
        if (userRepository.findByEmail(user.getEmail()) !=null) return false;
        user.setActive(true);
        user.getRole().add(Role.ROLE_USER);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        log.info("Save new user email: {}", user.getEmail());
        userRepository.save(user);
        return true;
    }
    public boolean saveAdmin(User user){
        if (userRepository.findByEmail(user.getEmail()) !=null) return false;
        user.setActive(true);
        user.getRole().add(Role.ROLE_ADMIN);
        log.info("Save new user email: {}", user.getEmail());
        return true;
    }
}
