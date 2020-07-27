package com.ironhack.edgeservice.DTO;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.ironhack.edgeservice.enums.TaskCategory;
import com.ironhack.edgeservice.enums.TaskStatus;
import com.ironhack.edgeservice.enums.TaskUrgency;
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

    private Long responsibleID;

    @NotNull
    private String responsibleEmail;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate startDate;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate endDate;
}
