package com.ironhack.notificationclient.service;

import com.ironhack.notificationclient.DTO.NotificationDTO;
import com.ironhack.notificationclient.DTO.ResponseDTO;
import com.ironhack.notificationclient.exceptions.NotFoundException;
import com.ironhack.notificationclient.model.Notification;
import com.ironhack.notificationclient.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService {
    @Autowired
    private NotificationRepository notificationRepository;

    public List<Notification> findAllByUser(Long userID){
        return notificationRepository.findAllByUserID(userID);
    }

    public Notification addNotification(NotificationDTO notificationDTO){
        Notification notification = new Notification(notificationDTO.getMessage(), notificationDTO.getUserID());
        return notificationRepository.save(notification);
    }

    public ResponseDTO deleteNotification(Long id){
        Notification notification = notificationRepository.findById(id).orElseThrow(() -> new NotFoundException("Notification not found"));
        notificationRepository.deleteById(id);

        return new ResponseDTO("OK", "Notification removed");
    }
}
