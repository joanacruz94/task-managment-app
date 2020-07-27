package com.ironhack.edgeservice.controller.implementation;

import com.ironhack.edgeservice.DTO.*;
import com.ironhack.edgeservice.model.Project;
import com.ironhack.edgeservice.model.UserProject;
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

    @GetMapping("/projectsByUser/{userID}")
    @ResponseStatus(HttpStatus.OK)
    public List<Project> findAllByUser(@PathVariable Long userID){
        return projectService.findAllByUser(userID);
    }

    @GetMapping("/membersOfProject/{projectID}")
    @ResponseStatus(HttpStatus.OK)
    public List<UserProjectDTO> findMembersOfProject(@PathVariable Long projectID){
        return projectService.findMembersOfProject(projectID);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public Project addProject(@RequestBody ProjectUserDTO project){
        return projectService.addProject(project);
    }

    @PatchMapping("/updateDescription")
    @ResponseStatus(HttpStatus.OK)
    public ResponseDTO updateDescription(@RequestBody ProjectDTO project){
        return projectService.updatePartialDescription(project);
    }

    @PostMapping("/addMember")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseDTO addMemberToProject(@RequestBody AddUserProject projectDTO){
        return projectService.addMemberToProject(projectDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseDTO deleteProject(@PathVariable Long id){
        return projectService.deleteProject(id);
    }
}
