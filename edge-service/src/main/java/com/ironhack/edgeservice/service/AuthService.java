package com.ironhack.edgeservice.service;


import com.ironhack.edgeservice.DTO.JwtAuthenticationResponse;
import com.ironhack.edgeservice.DTO.UserDTO;
import com.ironhack.edgeservice.DTO.UserLoginRequest;
import com.ironhack.edgeservice.client.UserClient;
import com.ironhack.edgeservice.model.User;
import com.ironhack.edgeservice.security.JwtTokenProvider;
import com.ironhack.edgeservice.security.UserPrincipal;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AuthService {
    @Autowired
    UserClient userClient;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider tokenProvider;

    private static final Logger LOGGER = LogManager.getLogger(AuthService.class);

    public JwtAuthenticationResponse authenticateUser(UserLoginRequest userLoginRequest) {
        System.out.println(userLoginRequest.getEmail());
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userLoginRequest.getEmail(),
                        userLoginRequest.getPassword()
                )
        );

        System.out.println("PASSEDDD");
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = tokenProvider.generateToken(authentication);
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        LOGGER.info("User with [username: {}] has logged in", userPrincipal.getUsername());

        JwtAuthenticationResponse response = new JwtAuthenticationResponse(jwt);
        response.setUserRole(userPrincipal.getRole());
        response.setUsername(userPrincipal.getUsername());

        return response;
    }

    public User signUpUser(UserDTO userDTO){
        return userClient.createUser(userDTO);
    }
}