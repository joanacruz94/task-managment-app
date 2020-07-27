package com.ironhack.edgeservice.service;

import com.ironhack.edgeservice.DTO.NotificationDTO;
import com.ironhack.edgeservice.DTO.ResponseDTO;
import com.ironhack.edgeservice.DTO.TaskDTO;
import com.ironhack.edgeservice.DTO.TaskPostDTO;
import com.ironhack.edgeservice.client.NotificationClient;
import com.ironhack.edgeservice.client.TaskClient;
import com.ironhack.edgeservice.client.UserClient;
import com.ironhack.edgeservice.exceptions.NotFoundException;
import com.ironhack.edgeservice.model.Task;
import com.ironhack.edgeservice.model.User;
import feign.FeignException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TaskService {
    @Autowired
    TaskClient taskClient;

    @Autowired
    UserClient userClient;

    @Autowired
    NotificationClient notificationClient;

    private static final Logger LOGGER = LogManager.getLogger(TaskService.class);

    public Task findById(Long id) {
        Task task = null;
        try {
            task = taskClient.findById(id);
        } catch(FeignException e) {
            if(e.status() == 404) throw new NotFoundException(e.contentUTF8().split(",")[1].split(":")[1].replaceAll("\"|}", ""));
        }
        return task;
    }

    public List<Task> findAll() throws FeignException{
        return taskClient.findAll();
    }

    public List<Task> findAllByUser(Long userID){
        return taskClient.findAllByUser(userID);
    }

    public List<Task> findAllByProject(Long projectID){
        return taskClient.findAllByProject(projectID);
    }

    public List<Task> findAllByUserAndProject(Long projectID, Long userID){
        return taskClient.findAllByUserAndProject(projectID, userID);
    }

    @Transactional
    public Task addTask(TaskPostDTO task){
        User user = userClient.findByEmail(task.getResponsibleEmail());
        task.setResponsibleID(user.getId());
        NotificationDTO notification = new NotificationDTO("New task was attributed to you", user.getId());
        notificationClient.addNotification(notification);
        return taskClient.addTask(task);
    }

    public ResponseDTO updatePartialStatus(TaskDTO taskDTO){
        return taskClient.updateStatus(taskDTO);
    }

    public ResponseDTO deleteTask(Long id) {
        return taskClient.deleteTask(id);}
}
