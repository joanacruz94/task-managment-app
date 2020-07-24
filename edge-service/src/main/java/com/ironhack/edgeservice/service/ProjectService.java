package com.ironhack.edgeservice.service;

import com.ironhack.edgeservice.DTO.ProjectDTO;
import com.ironhack.edgeservice.DTO.ProjectUserDTO;
import com.ironhack.edgeservice.DTO.ResponseDTO;
import com.ironhack.edgeservice.client.ProjectClient;
import com.ironhack.edgeservice.model.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {
    @Autowired
    ProjectClient projectClient;

    public Project findById(Long id) {
        return projectClient.findById(id);
    }

    public List<Project> findAll(){
        return projectClient.findAll();
    }

    public List<Project> findAllByUser(Long userID){
        return projectClient.findAllByUser(userID);
    }

    public Project addProject(ProjectUserDTO projectUserDTO){
        return projectClient.addProject(projectUserDTO);
    }

    public ResponseDTO updatePartialDescription(ProjectDTO projectDTO){
        return projectClient.updateDescription(projectDTO);
    }
}
