package com.ironhack.edgeservice.client;

import com.ironhack.edgeservice.DTO.ProjectDTO;
import com.ironhack.edgeservice.DTO.ProjectUserDTO;
import com.ironhack.edgeservice.DTO.ResponseDTO;
import com.ironhack.edgeservice.model.Project;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "project-client")
public interface ProjectClient {
    @GetMapping("/project/getById/{id}")
    Project findById(@PathVariable Long id);

    @GetMapping("/project/projects")
    List<Project> findAll();

    @GetMapping("/project/projectsByUser")
    List<Project> findAllByUser(@PathVariable Long userID);

    @PostMapping("/project")
    Project addProject(@RequestBody ProjectUserDTO project);

    @PatchMapping("/project/updateDescription")
    ResponseDTO updateDescription(@RequestBody ProjectDTO project);
}
