package com.example.personmenegement.repository;

import com.example.personmenegement.entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<TaskEntity, Long> {// todo лучше используй JpaRepository  //  DONE
}
