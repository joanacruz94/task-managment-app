package com.ironhack.edgeservice.service;

import com.ironhack.edgeservice.DTO.NotificationDTO;
import com.ironhack.edgeservice.DTO.ResponseDTO;
import com.ironhack.edgeservice.client.NotificationClient;
import com.ironhack.edgeservice.model.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService {
    @Autowired
    NotificationClient notificationClient;

    public List<Notification> findAllByUser(Long userID){
        return notificationClient.findAllByUser(userID);
    }

    public Notification addNotification(NotificationDTO notificationDTO){
        return notificationClient.addNotification(notificationDTO);
    }

    public ResponseDTO deleteNotification(Long notificationID){
        return notificationClient.deleteNotification(notificationID);
    }
}
