package com.ironhack.edgeservice.model;

import com.ironhack.edgeservice.enums.RoleName;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Role {
    private Integer id;

    private RoleName name;
}