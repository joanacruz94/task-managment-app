package com.ironhack.taskclient.repository;
import com.ironhack.taskclient.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findAllByResponsibleID(Long userID);

    List<Task> findAllByProjectID(Long projectID);

    List<Task> findAllByProjectIDAndResponsibleID(Long projectID, Long userID);
}