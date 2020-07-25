package com.ironhack.taskclient.controller;

import com.ironhack.taskclient.DTO.ResponseDTO;
import com.ironhack.taskclient.DTO.TaskDTO;
import com.ironhack.taskclient.DTO.TaskPostDTO;
import com.ironhack.taskclient.model.Task;
import com.ironhack.taskclient.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @GetMapping("/getById/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Task findById(@PathVariable Long id) {
        return taskService.findById(id);
    }

    @GetMapping("/tasks")
    @ResponseStatus(HttpStatus.OK)
    public List<Task> findAll(){
        return taskService.findAll();
    }

    @GetMapping("/tasksUser/{userID}")
    @ResponseStatus(HttpStatus.OK)
    public List<Task> findAllByUser(@PathVariable Long userID){
        return taskService.findAllByUser(userID);
    }

    @GetMapping("/tasksProject/{projectID}")
    @ResponseStatus(HttpStatus.OK)
    public List<Task> findAllByProject(@PathVariable Long projectID){
        return taskService.findAllByProject(projectID);
    }

    @GetMapping("/tasksProjectUser/{userID}/{projectID}")
    @ResponseStatus(HttpStatus.OK)
    public List<Task> findAllByUserAndProject(@PathVariable Long projectID, @PathVariable Long userID){
        return taskService.findAllByUserAndProject(projectID, userID);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public Task addTask(@RequestBody TaskPostDTO task){
        return taskService.addTask(task);
    }

    @PutMapping("/updateStatus")
    @ResponseStatus(HttpStatus.OK)
    public ResponseDTO updateStatus(@RequestBody TaskDTO taskDTO){
        return taskService.updatePartialStatus(taskDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseDTO deleteTask(@PathVariable Long id){
        return taskService.deleteTask(id);
    }

    @DeleteMapping("/tasksProject/{projectID}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseDTO deleteTasksFromProject(@PathVariable Long projectID){
        return taskService.deleteTasksFromProject(projectID);
    }
}
