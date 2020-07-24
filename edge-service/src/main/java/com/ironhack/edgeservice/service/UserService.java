package com.ironhack.edgeservice.service;

import com.ironhack.edgeservice.client.UserClient;
import com.ironhack.edgeservice.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserClient userClient;

    public User findUserById(Long id){
        return userClient.findById(id);
    }

    public User findUserByEmail(String email){
        User user = userClient.findByUsername(email);
        return user;
    }

}
