package com.ironhack.edgeservice.client;

import com.ironhack.edgeservice.DTO.ResponseDTO;
import com.ironhack.edgeservice.DTO.TaskDTO;
import com.ironhack.edgeservice.DTO.TaskPostDTO;
import com.ironhack.edgeservice.model.Task;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "task-client", url = "https://taskey-task-client.herokuapp.com/")
public interface TaskClient {
    @GetMapping("/task/getById/{id}")
    Task findById(@PathVariable Long id);

    @GetMapping("/task/tasks")
    List<Task> findAll();

    @GetMapping("/task/tasksUser/{userID}")
    List<Task> findAllByUser(@PathVariable Long userID);

    @GetMapping("/task/tasksProject/{projectID}")
    List<Task> findAllByProject(@PathVariable Long projectID);

    @GetMapping("/task/tasksProjectUser/{userID}/{projectID}")
    List<Task> findAllByUserAndProject(@PathVariable Long projectID, @PathVariable Long userID);

    @PostMapping("/task")
    Task addTask(@RequestBody TaskPostDTO task);

    @PutMapping("/task/updateStatus")
    ResponseDTO updateStatus(@RequestBody TaskDTO taskDTO);

    @DeleteMapping("/task/{id}")
    ResponseDTO deleteTask(@PathVariable Long id);

    @DeleteMapping("/task/tasksProject/{projectID}")
    @ResponseStatus(HttpStatus.OK)
    ResponseDTO deleteTasksFromProject(@PathVariable Long projectID);
}
