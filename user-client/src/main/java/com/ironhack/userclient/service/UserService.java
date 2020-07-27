package com.ironhack.userclient.service;

import com.ironhack.userclient.DTO.UserDTO;
import com.ironhack.userclient.enums.RoleName;
import com.ironhack.userclient.exceptions.ConflictException;
import com.ironhack.userclient.exceptions.NotFoundException;
import com.ironhack.userclient.model.User;
import com.ironhack.userclient.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    PasswordEncoder passwordEncoder;

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new NotFoundException("User not found"));
    }

    public List<User> findAll(){
        return userRepository.findAll();
    }

    @Transactional
    public User addUser(UserDTO userDTO){
        User existUser = findByEmail(userDTO.getEmail());
        if(existUser != null) {
            return null;
        }
        User user = new User(userDTO.getName(), userDTO.getEmail(), passwordEncoder.encode(userDTO.getPassword()));
        user.setRole(RoleName.ROLE_FREE);
        return userRepository.save(user);
    }
}