package com.ironhack.edgeservice.service;

import com.ironhack.edgeservice.DTO.ResponseDTO;
import com.ironhack.edgeservice.DTO.TaskDTO;
import com.ironhack.edgeservice.client.TaskClient;
import com.ironhack.edgeservice.exceptions.NotFoundException;
import com.ironhack.edgeservice.model.Task;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    @Autowired
    TaskClient taskClient;

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

    public Task addTask(Task task){
        return taskClient.addTask(task);
    }

    public ResponseDTO updatePartialStatus(TaskDTO taskDTO){
        return taskClient.updateStatus(taskDTO);
    }
}
