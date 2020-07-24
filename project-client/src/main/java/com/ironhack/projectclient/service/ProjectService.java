package com.ironhack.projectclient.service;


import com.ironhack.projectclient.DTO.ProjectDTO;
import com.ironhack.projectclient.DTO.ProjectUserDTO;
import com.ironhack.projectclient.DTO.ResponseDTO;
import com.ironhack.projectclient.model.Project;
import com.ironhack.projectclient.model.UserProject;
import com.ironhack.projectclient.repository.ProjectRepository;
import com.ironhack.projectclient.repository.UserProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectService {
    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private UserProjectRepository userProjectRepository;

    public Project findById(Long id) {
        return projectRepository.findById(id).orElseThrow(() -> new RuntimeException("Project not found"));
    }

    public List<Project> findAll(){
        return projectRepository.findAll();
    }

    public List<Project> findAllByUser(Long userID){
        return userProjectRepository.findAllByUser(userID).
                stream().map((value) -> findById(value.getProjectID())).
                collect(Collectors.toList());
    }

    public Project addProject(ProjectUserDTO projectUserDTO){
        UserProject userProject = new UserProject();
        userProject.setProjectID(projectUserDTO.getProjectID());
        userProject.setUserID(projectUserDTO.getUserID());
        userProjectRepository.save(userProject);
        Project project = new Project(projectUserDTO.getName());
        if(!projectUserDTO.getDescription().isEmpty()) project.setDescription(projectUserDTO.getDescription());
        return projectRepository.save(project);
    }

    public ResponseDTO updatePartialDescription(ProjectDTO projectDTO){
        Project project = projectRepository.findById(projectDTO.getProjectID()).orElseThrow(() -> new RuntimeException("Project not found"));
        project.setDescription(projectDTO.getDescription());
        projectRepository.save(project);

        return new ResponseDTO("OK", "Project description updated");
    }
}