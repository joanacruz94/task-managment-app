package com.ironhack.projectclient.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProjectUserDTO {
    @NotNull
    private Long userID;

    @NotNull
    private String name;
    private String description;
}
