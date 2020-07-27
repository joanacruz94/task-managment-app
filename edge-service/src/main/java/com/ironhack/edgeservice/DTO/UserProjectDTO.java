package com.ironhack.edgeservice.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserProjectDTO {
    @NotNull
    private String userEmail;

    @NotNull
    private boolean isOwner;
}
