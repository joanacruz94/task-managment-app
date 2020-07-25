package com.ironhack.taskclient.DTO;

import com.ironhack.taskclient.enums.TaskCategory;
import com.ironhack.taskclient.enums.TaskStatus;
import com.ironhack.taskclient.enums.TaskUrgency;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TaskPostDTO {
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
