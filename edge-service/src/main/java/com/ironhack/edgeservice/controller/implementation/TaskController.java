package com.ironhack.edgeservice.controller.implementation;

import com.ironhack.edgeservice.DTO.ResponseDTO;
import com.ironhack.edgeservice.DTO.TaskDTO;
import com.ironhack.edgeservice.DTO.TaskPostDTO;
import com.ironhack.edgeservice.model.Task;
import com.ironhack.edgeservice.service.TaskService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/task")
public class TaskController {
    @Autowired
    TaskService taskService;

    @ApiOperation(value = "Gets a task by its id.")
    @GetMapping("/getById/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Task findById(@PathVariable Long id) {
        return taskService.findById(id);
    }

    @ApiOperation(value = "Get all tasks in the system.")
    @GetMapping("/tasks")
    @ResponseStatus(HttpStatus.OK)
    public List<Task> findAll(){
        return taskService.findAll();
    }

    @ApiOperation(value = "Get all tasks of an user.")
    @GetMapping("/tasksUser/{userID}")
    @ResponseStatus(HttpStatus.OK)
    public List<Task> findAllByUser(@PathVariable Long userID){
        return taskService.findAllByUser(userID);
    }

    @ApiOperation(value = "Get all tasks of a project.")
    @GetMapping("/tasksProject/{projectID}")
    @ResponseStatus(HttpStatus.OK)
    public List<Task> findAllByProject(@PathVariable Long projectID){
        return taskService.findAllByProject(projectID);
    }

    @ApiOperation(value = "Get all tasks of a project corresponding to an user.")
    @GetMapping("/tasksProjectUser/{userID}/{projectID}")
    @ResponseStatus(HttpStatus.OK)
    public List<Task> findAllByUserAndProject(@PathVariable Long projectID, @PathVariable Long userID) {
        return taskService.findAllByUserAndProject(projectID, userID);
    }

    @ApiOperation(value = "Insert a new task in the system.")
    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public Task addTask(@RequestBody TaskPostDTO task){
        return taskService.addTask(task);
    }

    @ApiOperation(value = "Partial update. Update status of task. values := NEW, IN_PROGRESS, DONE")
    @PatchMapping("/updateStatus")
    @ResponseStatus(HttpStatus.OK)
    public ResponseDTO updateStatus(@RequestBody TaskDTO taskDTO){
        return taskService.updatePartialStatus(taskDTO);
    }

    @ApiOperation(value = "Removes a task.")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseDTO deleteTask(@PathVariable Long id) {
        return taskService.deleteTask(id);
    }
}
