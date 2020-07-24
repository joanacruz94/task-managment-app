package com.ironhack.taskclient.DTO;

import com.ironhack.taskclient.enums.TaskStatus;
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
