package com.ironhack.projectclient.service;


import com.ironhack.projectclient.DTO.AddUserProject;
import com.ironhack.projectclient.DTO.ProjectDTO;
import com.ironhack.projectclient.DTO.ProjectUserDTO;
import com.ironhack.projectclient.DTO.ResponseDTO;
import com.ironhack.projectclient.exceptions.NotFoundException;
import com.ironhack.projectclient.model.Project;
import com.ironhack.projectclient.model.UserProject;
import com.ironhack.projectclient.repository.ProjectRepository;
import com.ironhack.projectclient.repository.UserProjectRepository;
import org.apache.catalina.User;
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
        return projectRepository.findById(id).orElseThrow(() -> new NotFoundException("Project not found"));
    }

    public List<Project> findAll(){
        return projectRepository.findAll();
    }

    public List<Project> findAllByUser(Long userID){
        return userProjectRepository.findByUserID(userID)
                .stream().map((value) -> findById(value.getProjectID()))
                .collect(Collectors.toList());
    }

    public List<UserProject> findMembersOfProject(Long projectID){
        return userProjectRepository.findByProjectID(projectID);
    }

    public Project addProject(ProjectUserDTO projectUserDTO){
        UserProject userProject = new UserProject();
        userProject.setProjectID(projectUserDTO.getProjectID());
        userProject.setUserID(projectUserDTO.getUserID());
        userProject.setOwner(true);
        userProjectRepository.save(userProject);
        Project project = new Project(projectUserDTO.getName());
        if(!projectUserDTO.getDescription().isEmpty()) project.setDescription(projectUserDTO.getDescription());
        return projectRepository.save(project);
    }

    public ResponseDTO addMemberToProject(AddUserProject projectDTO){
        UserProject userProject = new UserProject();
        userProject.setProjectID(projectDTO.getProjectID());
        userProject.setUserID(projectDTO.getUserID());
        userProject.setOwner(false);
        userProjectRepository.save(userProject);
        return new ResponseDTO("OK", "Member added to project");
    }

    public ResponseDTO updatePartialDescription(ProjectDTO projectDTO){
        Project project = projectRepository.findById(projectDTO.getProjectID()).orElseThrow(() -> new NotFoundException("Project not found"));
        project.setDescription(projectDTO.getDescription());
        projectRepository.save(project);

        return new ResponseDTO("OK", "Project description updated");
    }

    public ResponseDTO deleteProject(Long id){
        Project project = projectRepository.findById(id).orElseThrow(() -> new NotFoundException("Project not found"));
        projectRepository.deleteById(id);
        List<UserProject> entries = findMembersOfProject(id);
        userProjectRepository.deleteAll(entries);
        return new ResponseDTO("OK", "Project removed");
    }
}