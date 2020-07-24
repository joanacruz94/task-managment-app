package com.ironhack.projectclient.repository;

import com.ironhack.projectclient.model.UserProject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserProjectRepository extends JpaRepository<UserProject,Long> {
    @Query(value = "SELECT * FROM user_projects up\n" +
            "WHERE up.user_id = :userID",
            nativeQuery = true)
    List<UserProject> findAllByUser(Long userID);
}
