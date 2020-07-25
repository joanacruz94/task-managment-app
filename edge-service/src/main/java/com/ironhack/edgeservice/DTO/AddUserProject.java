package com.ironhack.edgeservice.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddUserProject {
    @NotNull
    private Long projectID;

    @NotNull
    private Long userID;
}
