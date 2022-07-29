package com.example.personmenegement.repository;

import com.example.personmenegement.entity.TaskEntity;
import org.springframework.data.repository.CrudRepository;

public interface TaskRepository  extends CrudRepository<TaskEntity, Long> {
}
