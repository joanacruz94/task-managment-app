package com.ironhack.userclient.controller;

import com.ironhack.userclient.DTO.UserDTO;
import com.ironhack.userclient.model.User;
import com.ironhack.userclient.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/getByEmail/{email}")
    @ResponseStatus(HttpStatus.OK)
    public User findByUsername(@PathVariable String email){
        return userService.findByEmail(email);
    }

    @GetMapping("/getById/{id}")
    @ResponseStatus(HttpStatus.OK)
    public User findById(@PathVariable Long id) {
        return userService.findById(id);
    }

    @GetMapping("/users")
    @ResponseStatus(HttpStatus.OK)
    public List<User> findAll(){
        return userService.findAll();
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public User addUser(@RequestBody UserDTO user){
        return userService.addUser(user);
    }
}
