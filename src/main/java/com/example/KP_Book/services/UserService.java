package com.example.KP_Book.services;

import com.example.KP_Book.entity.Enum.Role;
import com.example.KP_Book.entity.User;
import com.example.KP_Book.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public boolean saveUser(User user) throws IOException {
        if (userRepository.findByEmail(user.getEmail()) !=null) return false;
        user.setActive(true);
        user.getRole().add(Role.ROLE_ADMIN);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        log.info("Save new user email: {}", user.getEmail());
        userRepository.save(user);
        return true;
    }
    public boolean saveAdmin(User user){
        if (userRepository.findByEmail(user.getEmail()) !=null) return false;
        user.setActive(true);
        user.getRole().add(Role.ROLE_USER);
        log.info("Save new user email: {}", user.getEmail());
        return true;
    }
    public List<User> allUser(){
        return  userRepository.findAll();
    }
    public void banUser(Long id){
        User badUser=userRepository.findById(id).orElse(null);
        if (badUser!=null){
           if (badUser.isActive()){
               badUser.setActive(false);
               log.info("Ban user with id = {}; email = {}", badUser.getId(), badUser.getEmail());
           }else badUser.setActive(true);
        }else log.info("Failed to block user with email = {};", badUser.getEmail());

        userRepository.save(badUser);
    }

    public void updateUser(User user,Long id) {
        User userFromDB=userRepository.findById(id).orElse(null);
        if (user.getName()!=null) userFromDB.setName(user.getName());
        if (user.getEmail()!=null) userFromDB.setEmail(user.getEmail());
        if (user.getPhoneNumber()!=null) userFromDB.setPhoneNumber(user.getPhoneNumber());
        userRepository.save(userFromDB);
    }
}
