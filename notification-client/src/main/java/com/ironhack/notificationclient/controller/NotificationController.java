package com.ironhack.notificationclient.controller;

import com.ironhack.notificationclient.DTO.NotificationDTO;
import com.ironhack.notificationclient.DTO.ResponseDTO;
import com.ironhack.notificationclient.model.Notification;
import com.ironhack.notificationclient.service.NotificationService;
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

    @PostMapping("/notifications")
    @ResponseStatus(HttpStatus.CREATED)
    public List<Notification> addListNotifications(@RequestBody List<NotificationDTO> notifications){
        return notificationService.addListNotifications(notifications);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseDTO deleteNotification(@PathVariable Long id){
        return notificationService.deleteNotification(id);
    }
}
