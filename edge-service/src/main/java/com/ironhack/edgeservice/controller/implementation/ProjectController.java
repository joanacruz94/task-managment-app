package com.ironhack.edgeservice.controller.implementation;

import com.ironhack.edgeservice.DTO.*;
import com.ironhack.edgeservice.model.Project;
import com.ironhack.edgeservice.model.UserProject;
import com.ironhack.edgeservice.service.ProjectService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/project")
public class ProjectController {
    @Autowired
    ProjectService projectService;

    @ApiOperation(value = "Gets a project by id.")
    @GetMapping("/getById/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Project findById(@PathVariable Long id){
        return projectService.findById(id);
    }

    @ApiOperation(value = "Get all projects in the system.")
    @GetMapping("/projects")
    @ResponseStatus(HttpStatus.OK)
    public List<Project> findAll(){
        return projectService.findAll();
    }

    @ApiOperation(value = "Get all projects of an user in the system.")
    @GetMapping("/projectsByUser/{userID}")
    @ResponseStatus(HttpStatus.OK)
    public List<Project> findAllByUser(@PathVariable Long userID){
        return projectService.findAllByUser(userID);
    }

    @ApiOperation(value = "Get all members of a project.")
    @GetMapping("/membersOfProject/{projectID}")
    @ResponseStatus(HttpStatus.OK)
    public List<UserProjectDTO> findMembersOfProject(@PathVariable Long projectID){
        return projectService.findMembersOfProject(projectID);
    }

    @ApiOperation(value = "Insert a new project.")
    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public Project addProject(@RequestBody ProjectUserDTO project){
        return projectService.addProject(project);
    }

    @ApiOperation(value = "Partial update. Updates description of project.")
    @PatchMapping("/updateDescription")
    @ResponseStatus(HttpStatus.OK)
    public ResponseDTO updateDescription(@RequestBody ProjectDTO project){
        return projectService.updatePartialDescription(project);
    }

    @ApiOperation(value = "Add a member to a project.")
    @PostMapping("/addMember")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseDTO addMemberToProject(@RequestBody AddUserProject projectDTO){
        return projectService.addMemberToProject(projectDTO);
    }

    @ApiOperation(value = "Removes a project and corresponding tasks.")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseDTO deleteProject(@PathVariable Long id){
        return projectService.deleteProject(id);
    }
}
