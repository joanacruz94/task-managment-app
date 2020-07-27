package com.ironhack.edgeservice.client;

import com.ironhack.edgeservice.DTO.NotificationDTO;
import com.ironhack.edgeservice.DTO.ResponseDTO;
import com.ironhack.edgeservice.model.Notification;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@FeignClient(name = "notification-client", url = "https://taskey-not-client.herokuapp.com/")
public interface NotificationClient {

    @GetMapping("/notification/notificationsByUser/{userID}")
    List<Notification> findAllByUser(@PathVariable Long userID);

    @PostMapping("/notification")
    Notification addNotification(@RequestBody NotificationDTO notificationDTO);

    @PostMapping("/notification/notifications")
    List<Notification> addListNotifications(@RequestBody List<NotificationDTO> notificationList);

    @DeleteMapping("/notification/{id}")
    ResponseDTO deleteNotification(@PathVariable Long id);
}
