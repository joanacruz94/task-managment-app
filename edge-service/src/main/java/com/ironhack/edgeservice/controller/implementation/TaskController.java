package com.ironhack.edgeservice.controller.implementation;

import com.ironhack.edgeservice.DTO.ResponseDTO;
import com.ironhack.edgeservice.DTO.TaskDTO;
import com.ironhack.edgeservice.DTO.TaskPostDTO;
import com.ironhack.edgeservice.model.Task;
import com.ironhack.edgeservice.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/task")
public class TaskController {
    @Autowired
    TaskService taskService;

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
    public List<Task> findAllByUserAndProject(@PathVariable Long projectID, @PathVariable Long userID) {
        return taskService.findAllByUserAndProject(projectID, userID);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public Task addTask(@RequestBody TaskPostDTO task){
        return taskService.addTask(task);
    }

    @PatchMapping("/updateStatus")
    @ResponseStatus(HttpStatus.OK)
    public ResponseDTO updateStatus(@RequestBody TaskDTO taskDTO){
        return taskService.updatePartialStatus(taskDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseDTO deleteTask(@PathVariable Long id) {
        return taskService.deleteTask(id);
    }
}
