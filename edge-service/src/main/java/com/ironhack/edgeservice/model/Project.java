package com.ironhack.edgeservice.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class Project {
    private Long id;

    @NotNull
    private String name;

    private String description;

    public Project(String name){
        this.name = name;
    }
}