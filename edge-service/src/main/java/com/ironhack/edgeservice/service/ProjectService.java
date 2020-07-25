package com.ironhack.edgeservice.service;

import com.ironhack.edgeservice.DTO.*;
import com.ironhack.edgeservice.client.NotificationClient;
import com.ironhack.edgeservice.client.ProjectClient;
import com.ironhack.edgeservice.client.TaskClient;
import com.ironhack.edgeservice.client.UserClient;
import com.ironhack.edgeservice.model.Project;
import com.ironhack.edgeservice.model.User;
import com.ironhack.edgeservice.model.UserProject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

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
        ResponseDTO response = projectClient.addMemberToProject(projectDTO);
        if(response.getStatus().equals("OK")) {
            Project project = projectClient.findById(projectDTO.getProjectID());
            List<UserProject> members = projectClient.findMembersOfProject(projectDTO.getProjectID());
            User newMember = userClient.findById(projectDTO.getUserID());
            User ownerProject = null;
            for(UserProject member : members) {
                if(!member.isOwner() && member.getUserID() != newMember.getId()) {
                    NotificationDTO notificationDTO = new NotificationDTO(newMember.getEmail() + " was added to the project " + project.getName(), member.getUserID());
                    notificationClient.addNotification(notificationDTO);
                } else {
                    ownerProject = userClient.findById(member.getUserID());
                }
            }
            NotificationDTO notificationNewMember = new NotificationDTO("You were added to the project " + project.getName() + " by " + ownerProject.getEmail(), newMember.getId());
            NotificationDTO notificationOwner = new NotificationDTO("You just added to the project " + project.getName() + " the member " + newMember.getEmail(), ownerProject.getId());
            notificationClient.addNotification(notificationNewMember);
            notificationClient.addNotification(notificationOwner);

            return new ResponseDTO("OK", "New member was added to the project");
        }
        return new ResponseDTO("Fail", "New member wasn't added to the project");
    }

    public List<UserProject> findMembersOfProject(Long projectID){
        return projectClient.findMembersOfProject(projectID);
    }

    public ResponseDTO deleteProject(Long id){
        ResponseDTO response = projectClient.deleteProject(id);
        if(response.getStatus().equals("OK")) {
            return taskClient.deleteTasksFromProject(id);
        } else return new ResponseDTO("Fail", "Project wasn't removed");
    }
}
