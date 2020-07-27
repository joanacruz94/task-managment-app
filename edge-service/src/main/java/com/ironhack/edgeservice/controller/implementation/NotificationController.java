package com.ironhack.edgeservice.controller.implementation;

import com.ironhack.edgeservice.DTO.NotificationDTO;
import com.ironhack.edgeservice.DTO.ResponseDTO;
import com.ironhack.edgeservice.model.Notification;
import com.ironhack.edgeservice.service.NotificationService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notification")
public class NotificationController {
    @Autowired
    NotificationService notificationService;

    @ApiOperation(value = "Get all notifications of an user.")
    @GetMapping("/notificationsByUser/{userID}")
    @ResponseStatus(HttpStatus.OK)
    public List<Notification> findAllByUser(@PathVariable Long userID){
        return notificationService.findAllByUser(userID);
    }

    @ApiOperation(value = "Insert a new notification in the system.")
    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public Notification addNotification(@RequestBody NotificationDTO notificationDTO){
        return notificationService.addNotification(notificationDTO);
    }

    @ApiOperation(value = "Removes a notification of the system.")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseDTO deleteNotification(@PathVariable Long id){
        return notificationService.deleteNotification(id);
    }
}
