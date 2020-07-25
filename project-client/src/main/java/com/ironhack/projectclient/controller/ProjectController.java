package com.ironhack.projectclient.controller;

import com.ironhack.projectclient.DTO.AddUserProject;
import com.ironhack.projectclient.DTO.ProjectDTO;
import com.ironhack.projectclient.DTO.ProjectUserDTO;
import com.ironhack.projectclient.DTO.ResponseDTO;
import com.ironhack.projectclient.model.Project;
import com.ironhack.projectclient.model.UserProject;
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

    @PostMapping("/addMember")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseDTO addMemberToProject(@RequestBody AddUserProject projectDTO){
        return projectService.addMemberToProject(projectDTO);
    }

    @GetMapping("/projectsByUser/{userID}")
    @ResponseStatus(HttpStatus.OK)
    public List<Project> findAllByUser(@PathVariable Long userID){
        return projectService.findAllByUser(userID);
    }


    @GetMapping("/membersOfProject/{projectID}")
    @ResponseStatus(HttpStatus.OK)
    public List<UserProject> findMembersOfProject(@PathVariable Long projectID){
        return projectService.findMembersOfProject(projectID);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public Project addProject(@RequestBody ProjectUserDTO project){
        return projectService.addProject(project);
    }

    @PutMapping("/updateDescription")
    @ResponseStatus(HttpStatus.OK)
    public ResponseDTO updateDescription(@RequestBody ProjectDTO project){
        return projectService.updatePartialDescription(project);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseDTO deleteProject(@PathVariable Long id){
        return projectService.deleteProject(id);
    }
}
