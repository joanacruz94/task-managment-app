package com.ironhack.taskclient.model;

import com.ironhack.taskclient.enums.TaskCategory;
import com.ironhack.taskclient.enums.TaskStatus;
import com.ironhack.taskclient.enums.TaskUrgency;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String description;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(length = 15)
    private TaskStatus status;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(length = 15)
    private TaskUrgency urgency;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(length = 15)
    private TaskCategory category;

    @NotNull
    @Column(name = "project_id")
    private Long projectID;

    @NotNull
    @Column(name = "responsible_id")
    private Long responsibleID;

    @Column(name = "start_date")
    private LocalDateTime startDate;

    @Column(name = "end_date")
    private LocalDateTime endDate;
}
