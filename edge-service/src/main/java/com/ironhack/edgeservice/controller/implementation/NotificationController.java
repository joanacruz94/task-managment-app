package com.ironhack.edgeservice.controller.implementation;

import com.ironhack.edgeservice.DTO.NotificationDTO;
import com.ironhack.edgeservice.DTO.ResponseDTO;
import com.ironhack.edgeservice.model.Notification;
import com.ironhack.edgeservice.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notification")
public class NotificationController {
    @Autowired
    NotificationService notificationService;

    @GetMapping("/notificationsByUser/{userID}")
    @ResponseStatus(HttpStatus.OK)
    public List<Notification> findAllByUser(@PathVariable Long userID){
        return notificationService.findAllByUser(userID);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public Notification addNotification(@RequestBody NotificationDTO notificationDTO){
        return notificationService.addNotification(notificationDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseDTO deleteNotification(@PathVariable Long id){
        return notificationService.deleteNotification(id);
    }
}
