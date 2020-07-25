package com.ironhack.taskclient.model;

import com.ironhack.taskclient.enums.TaskCategory;
import com.ironhack.taskclient.enums.TaskStatus;
import com.ironhack.taskclient.enums.TaskUrgency;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.cglib.core.Local;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
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
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    public Task(String description, TaskStatus status, TaskUrgency urgency, TaskCategory category, Long projectID, Long responsibleID, LocalDate startDate, LocalDate endDate) {
        this.description = description;
        this.status = status;
        this.urgency = urgency;
        this.category = category;
        this.projectID = projectID;
        this.responsibleID = responsibleID;
        if(startDate != null) this.startDate = startDate;
        else this.startDate = LocalDate.now();
        this.endDate = endDate;
    }
}
