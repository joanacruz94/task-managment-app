package com.ironhack.edgeservice.controller.implementation;

import com.ironhack.edgeservice.DTO.ProjectDTO;
import com.ironhack.edgeservice.DTO.ProjectUserDTO;
import com.ironhack.edgeservice.DTO.ResponseDTO;
import com.ironhack.edgeservice.model.Project;
import com.ironhack.edgeservice.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/project")
public class ProjectController {
    @Autowired
    ProjectService projectService;

    @GetMapping("/getById/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Project findById(@PathVariable Long id){
        return projectService.findById(id);
    }

    @GetMapping("/projects")
    @ResponseStatus(HttpStatus.OK)
    public List<Project> findAll(){
        return projectService.findAll();
    }

    @GetMapping("/projectsByUser")
    @ResponseStatus(HttpStatus.OK)
    public List<Project> findAllByUser(@PathVariable Long userID){
        return projectService.findAllByUser(userID);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public Project addProject(@RequestBody ProjectUserDTO project){
        return projectService.addProject(project);
    }

    @PatchMapping("/updateDescription")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseDTO updateDescription(@RequestBody ProjectDTO project){
        return projectService.updatePartialDescription(project);
    }
}
