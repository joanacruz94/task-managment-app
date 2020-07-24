package com.ironhack.edgeservice.DTO;

import com.ironhack.edgeservice.enums.TaskStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TaskDTO {
    private Long taskID;
    private TaskStatus status;
}
