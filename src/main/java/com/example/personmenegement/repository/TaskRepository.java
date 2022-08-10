package com.example.personmenegement.repository;

import com.example.personmenegement.entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TaskRepository extends JpaRepository<TaskEntity, Long> {

    Optional<TaskEntity> findByUid(String uid);// todo лучше используй JpaRepository  //  DONE
}
