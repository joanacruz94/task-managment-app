package com.ironhack.edgeservice.client;

import com.ironhack.edgeservice.DTO.AddUserProject;
import com.ironhack.edgeservice.DTO.ProjectDTO;
import com.ironhack.edgeservice.DTO.ProjectUserDTO;
import com.ironhack.edgeservice.DTO.ResponseDTO;
import com.ironhack.edgeservice.model.Project;
import com.ironhack.edgeservice.model.UserProject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "project-client")
public interface ProjectClient {
    @GetMapping("/project/getById/{id}")
    Project findById(@PathVariable Long id);

    @GetMapping("/project/projects")
    List<Project> findAll();

    @GetMapping("/project/projectsByUser/{userID}")
    List<Project> findAllByUser(@PathVariable Long userID);

    @GetMapping("/project/membersOfProject/{projectID}")
    List<UserProject> findMembersOfProject(@PathVariable Long projectID);

    @PostMapping("/project")
    Project addProject(@RequestBody ProjectUserDTO project);

    @PutMapping("/project/updateDescription")
    ResponseDTO updateDescription(@RequestBody ProjectDTO project);

    @PostMapping("/project/addMember")
    ResponseDTO addMemberToProject(@RequestBody AddUserProject projectDTO);

    @DeleteMapping("/project/{id}")
    ResponseDTO deleteProject(@PathVariable Long id);
}
