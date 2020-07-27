package com.ironhack.notificationclient.model;

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
@Table(name = "notifications")
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String message;

    @NotNull
    @Column(name = "user_id")
    private Long userID;

    @NotNull
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public Notification(String message, Long userID){
        this.message = message;
        this.userID = userID;
        this.createdAt = LocalDateTime.now();
    }
}
