package com.ironhack.projectclient.controller;

import com.ironhack.projectclient.DTO.ProjectDTO;
import com.ironhack.projectclient.DTO.ProjectUserDTO;
import com.ironhack.projectclient.DTO.ResponseDTO;
import com.ironhack.projectclient.model.Project;
import com.ironhack.projectclient.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/project")
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    @GetMapping("/getById/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Project findById(@PathVariable Long id) {
        return projectService.findById(id);
    }

    @GetMapping("/projects")
    @ResponseStatus(HttpStatus.OK)
    public List<Project> findAll(){
        return projectService.findAll();
    }

    @GetMapping("/projectsByUser/{userID}")
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
