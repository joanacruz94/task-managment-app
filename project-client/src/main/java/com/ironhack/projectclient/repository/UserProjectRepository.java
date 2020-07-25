package com.ironhack.projectclient.repository;

import com.ironhack.projectclient.model.UserProject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserProjectRepository extends JpaRepository<UserProject,Long> {
    List<UserProject> findByUserID(Long userID);

    List<UserProject> findByProjectID(Long projectID);

    @Modifying
    @Query("delete from UserProject uj where uj.projectID=:projectID")
    void deleteUsersFromProject(@Param("projectID") Long projectID);
}
