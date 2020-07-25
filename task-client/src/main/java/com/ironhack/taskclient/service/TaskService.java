package com.ironhack.taskclient.service;

import com.ironhack.taskclient.DTO.ResponseDTO;
import com.ironhack.taskclient.DTO.TaskDTO;
import com.ironhack.taskclient.DTO.TaskPostDTO;
import com.ironhack.taskclient.exceptions.NotFoundException;
import com.ironhack.taskclient.model.Task;
import com.ironhack.taskclient.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    public Task findById(Long id) {
        return taskRepository.findById(id).orElseThrow(() -> new NotFoundException("Task not found"));
    }

    public List<Task> findAll(){
        return taskRepository.findAll();
    }

    public List<Task> findAllByUser(Long userID){
        return taskRepository.findAllByResponsibleID(userID);
    }

    public List<Task> findAllByProject(Long projectID){
        return taskRepository.findAllByProjectID(projectID);
    }

    public List<Task> findAllByUserAndProject(Long projectID, Long userID){
        return taskRepository.findAllByProjectIDAndResponsibleID(projectID, userID);
    }

    public Task addTask(TaskPostDTO taskDTO){
        Task task = new Task(taskDTO.getDescription(), taskDTO.getStatus(), taskDTO.getUrgency(), taskDTO.getCategory(), taskDTO.getProjectID(), taskDTO.getResponsibleID(), taskDTO.getStartDate(), taskDTO.getStartDate());
        return taskRepository.save(task);
    }

    public ResponseDTO updatePartialStatus(TaskDTO taskDTO){
        Task task = taskRepository.findById(taskDTO.getTaskID()).orElseThrow(() -> new NotFoundException("Task not found"));
        task.setStatus(taskDTO.getStatus());
        taskRepository.save(task);

        return new ResponseDTO("OK", "Status task updated");
    }

    public ResponseDTO deleteTask(Long id){
        Task task = taskRepository.findById(id).orElseThrow(() -> new NotFoundException("Task not found"));
        taskRepository.deleteById(id);

        return new ResponseDTO("OK", "Task removed");
    }

    public ResponseDTO deleteTasksFromProject(Long projectID){
        List<Task> tasks = findAllByProject(projectID);
        taskRepository.deleteAll(tasks);

        return new ResponseDTO("OK", "Tasks removed");
    }
}
