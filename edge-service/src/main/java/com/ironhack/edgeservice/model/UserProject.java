package com.ironhack.edgeservice.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class UserProject {
    private Long id;

    @NotNull
    private Long userID;

    @NotNull
    private Long projectID;

    @NotNull
    private boolean isOwner;
}
