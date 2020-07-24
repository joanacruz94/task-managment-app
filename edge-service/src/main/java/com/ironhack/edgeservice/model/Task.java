package com.ironhack.edgeservice.model;

import com.ironhack.edgeservice.enums.TaskCategory;
import com.ironhack.edgeservice.enums.TaskStatus;
import com.ironhack.edgeservice.enums.TaskUrgency;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class Task {
    private Long id;

    @NotNull
    private String description;

    @NotNull
    private String status;

    @NotNull
    private String urgency;

    @NotNull
    private String category;

    @NotNull
    private Long projectID;

    @NotNull
    private Long responsibleID;

    private LocalDateTime startDate;

    private LocalDateTime endDate;
}
