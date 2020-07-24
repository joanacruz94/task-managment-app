package com.ironhack.projectclient.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "user_projects")
public class UserProject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "user_id")
    private Long userID;

    @NotNull
    @Column(name = "project_id")
    private Long projectID;

    @NotNull
    @Column(name = "owner")
    private boolean isOwner;
}
