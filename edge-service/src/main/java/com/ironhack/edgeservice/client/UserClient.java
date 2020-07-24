package com.ironhack.edgeservice.client;

import com.ironhack.edgeservice.DTO.UserDTO;
import com.ironhack.edgeservice.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "user-client")
public interface UserClient {
    @GetMapping("/user/getByEmail/{email}")
    User findByUsername(@PathVariable String email);

    @GetMapping("/user/getById/{id}")
    User findById(@PathVariable Long id);

    @GetMapping("/user/users")
    List<User> findAllUsers();

    @PostMapping("/user")
    User createUser(@RequestBody UserDTO user);
}
