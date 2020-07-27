package com.ironhack.edgeservice.model;

import com.ironhack.edgeservice.enums.TaskCategory;
import com.ironhack.edgeservice.enums.TaskStatus;
import com.ironhack.edgeservice.enums.TaskUrgency;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class Task {
    private Long id;

    @NotNull
    private String description;

    @NotNull
    private TaskStatus status;

    @NotNull
    private TaskUrgency urgency;

    @NotNull
    private TaskCategory category;

    @NotNull
    private Long projectID;

    @NotNull
    private Long responsibleID;

    private LocalDate startDate;

    private LocalDate endDate;
}
