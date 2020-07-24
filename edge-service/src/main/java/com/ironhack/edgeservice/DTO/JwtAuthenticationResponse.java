package com.ironhack.edgeservice.DTO;

import com.ironhack.edgeservice.enums.RoleName;
import com.ironhack.edgeservice.model.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtAuthenticationResponse {
    private String accessToken;
    private String tokenType = "Bearer";
    private String username;
    private RoleName userRole;
    private Long userID;

    public JwtAuthenticationResponse(String accessToken) {
        this.accessToken = accessToken;
    }
}
