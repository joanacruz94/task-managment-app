package com.ironhack.edgeservice.security;

import com.ironhack.edgeservice.exceptions.NotFoundException;
import com.ironhack.edgeservice.model.User;
import com.ironhack.edgeservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private UserService userService;

    @Autowired
    public CustomUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws NotFoundException {
        User user = userService.findUserByEmail(email);
        System.out.println("HEREEE");
        if(user == null) throw new NotFoundException("Email doesn't exist in the system");
        return UserPrincipal.create(user);
    }

    @Transactional
    public UserDetails loadUserById(Long id) {
        User user = userService.findUserById(id);

        return UserPrincipal.create(user);
    }
}