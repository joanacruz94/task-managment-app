package com.ironhack.edgeservice.workers;

import com.ironhack.edgeservice.DTO.NotificationDTO;
import com.ironhack.edgeservice.client.NotificationClient;
import com.ironhack.edgeservice.client.TaskClient;
import com.ironhack.edgeservice.model.Notification;
import com.ironhack.edgeservice.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class Notifier {
    @Autowired
    NotificationClient notificationClient;

    @Autowired
    TaskClient taskClient;

    @Scheduled(cron = "0 0 12 * * ?")
    public void notifyDeadlineTasks() {
        LocalDate currentDate = LocalDate.now();
        List<Task> tasks = taskClient.findAll();
        List<Task> filteredTasks = tasks.stream().filter((task) -> {
                LocalDate endDate = task.getEndDate();
                long daysBetween = ChronoUnit.DAYS.between(endDate, currentDate);
                return daysBetween <= 3;
            }).collect(Collectors.toList());

         for(Task task : filteredTasks) {
             LocalDate endDate = task.getEndDate();
             long daysBetween = ChronoUnit.DAYS.between(endDate, currentDate);
             String message = "Deadline of your task with ID " + task.getId() +
                     " is in " + daysBetween + " days";
             NotificationDTO notification = new NotificationDTO(message, task.getResponsibleID());
             notificationClient.addNotification(notification);
         }
    }
}
