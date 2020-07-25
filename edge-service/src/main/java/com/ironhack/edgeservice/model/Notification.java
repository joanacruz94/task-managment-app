package com.ironhack.edgeservice.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class Notification {
    private Long id;

    @NotNull
    private String message;

    @NotNull
    private Long userID;

    @NotNull
    private LocalDateTime createdAt;

    public Notification(String message, Long userID){
        this.message = message;
        this.userID = userID;
        this.createdAt = LocalDateTime.now();
    }
}
