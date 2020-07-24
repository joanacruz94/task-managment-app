package com.ironhack.edgeservice.DTO;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class UserLoginRequest {
    @NotBlank
    private String email;

    @NotBlank
    private String password;
}