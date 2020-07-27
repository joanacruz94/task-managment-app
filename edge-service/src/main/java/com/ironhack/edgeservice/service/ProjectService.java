package com.ironhack.edgeservice.service;

import com.ironhack.edgeservice.DTO.*;
import com.ironhack.edgeservice.client.NotificationClient;
import com.ironhack.edgeservice.client.ProjectClient;
import com.ironhack.edgeservice.client.TaskClient;
import com.ironhack.edgeservice.client.UserClient;
import com.ironhack.edgeservice.exceptions.NotFoundException;
import com.ironhack.edgeservice.model.Project;
import com.ironhack.edgeservice.model.User;
import com.ironhack.edgeservice.model.UserProject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectService {
    @Autowired
    ProjectClient projectClient;

    @Autowired
    NotificationClient notificationClient;

    @Autowired
    UserClient userClient;

    @Autowired
    TaskClient taskClient;

    private static final Logger LOGGER = LogManager.getLogger(ProjectService.class);

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

    public ResponseDTO updatePartialDescription(ProjectDTO projectDTO) {
        return projectClient.updateDescription(projectDTO);
    }

    @Transactional
    public ResponseDTO addMemberToProject(AddUserProject projectDTO){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User newMember = userClient.findByEmail(projectDTO.getUserEmail());
        if(newMember == null) throw new NotFoundException("E-mail not registered");
        projectDTO.setUserID(newMember.getId());
        ResponseDTO response = projectClient.addMemberToProject(projectDTO);
        if(response.getStatus().equals("OK")) {
            Project project = projectClient.findById(projectDTO.getProjectID());
            List<UserProject> members = projectClient.findMembersOfProject(projectDTO.getProjectID());
            for(UserProject member : members) {
                if(member.getUserID() != newMember.getId()) {
                    NotificationDTO notificationDTO = new NotificationDTO(newMember.getEmail() + " was added to the project " + project.getName(), member.getUserID());
                    notificationClient.addNotification(notificationDTO);
                }
            }
            NotificationDTO notificationNewMember = new NotificationDTO("You were added to the project " + project.getName() + " by " + auth.getName(), newMember.getId());
            notificationClient.addNotification(notificationNewMember);

            return new ResponseDTO("OK", "New member was added to the project");
        }
        return new ResponseDTO("Fail", "New member wasn't added to the project");
    }

    public List<UserProjectDTO> findMembersOfProject(Long projectID){
        return projectClient.findMembersOfProject(projectID).stream()
                .map((member) -> {
                    User user = userClient.findById(member.getUserID());
                    return new UserProjectDTO(user.getEmail(), member.isOwner());
                })
                .collect(Collectors.toList());
    }

    @Transactional
    public ResponseDTO deleteProject(Long id){
        ResponseDTO response = projectClient.deleteProject(id);
        if(response.getStatus().equals("OK")) {
            return taskClient.deleteTasksFromProject(id);
        } else return new ResponseDTO("Fail", "Project wasn't removed");
    }
}
