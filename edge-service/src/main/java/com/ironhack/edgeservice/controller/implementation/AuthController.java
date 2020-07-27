package com.ironhack.edgeservice.controller.implementation;

import com.ironhack.edgeservice.DTO.JwtAuthenticationResponse;
import com.ironhack.edgeservice.DTO.UserDTO;
import com.ironhack.edgeservice.DTO.UserLoginRequest;
import com.ironhack.edgeservice.model.User;
import com.ironhack.edgeservice.service.AuthService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @ApiOperation(value = "Sign in method. Returns token type and token to send in every request that identifies the user.")
    @PostMapping("/sign-in")
    @ResponseStatus(HttpStatus.OK)
    public JwtAuthenticationResponse loginUser(@RequestBody @Valid UserLoginRequest loginRequest) {
        return authService.authenticateUser(loginRequest);
    }

    @ApiOperation(value = "Insert a new user in the system.")
    @PostMapping("/sign-up")
    @ResponseStatus(HttpStatus.OK)
    public User signUpUser(@RequestBody @Valid UserDTO userDTO) {
        return authService.signUpUser(userDTO);
    }

}
