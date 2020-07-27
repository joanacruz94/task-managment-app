package com.ironhack.edgeservice.service;

import com.ironhack.edgeservice.client.UserClient;
import com.ironhack.edgeservice.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserClient userClient;

    private static final Logger LOGGER = LogManager.getLogger(UserService.class);

    public User findUserById(Long id){
        return userClient.findById(id);
    }

    public User findUserByEmail(String email){
        User user = userClient.findByEmail(email);
        return user;
    }

}
