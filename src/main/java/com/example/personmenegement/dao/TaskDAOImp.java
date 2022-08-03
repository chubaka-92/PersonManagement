package com.example.personmenegement.dao;

import com.example.personmenegement.api.TaskDAO;
import com.example.personmenegement.entity.TaskEntity;
import com.example.personmenegement.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Repository
@RequiredArgsConstructor
public class TaskDAOImp implements TaskDAO {
    private final TaskRepository taskRepository;

    public TaskEntity findTaskById(Long id) {
        log.info("Was calling findTaskById. Input id: {}", id);
        return taskRepository.findById(id).orElse(null);
    }

    public List<TaskEntity> findTasks() {
        log.info("Was calling findTasks.");
        return (List<TaskEntity>) taskRepository.findAll();// todo лучше используй JpaRepository не нужны будут лишние касты
    }

    @Transactional
    public TaskEntity addTask(TaskEntity taskEntity) {
        log.info("Was calling addTask. Input taskEntity: {}", taskEntity.toString());// todo toString не обязательно писать
        return taskRepository.save(taskEntity);
    }

    @Transactional
    public TaskEntity updateTask(TaskEntity taskEntity) {
        log.info("Was calling updateTask. Input taskEntity: {}", taskEntity.toString());// todo toString не обязательно писать
        return taskRepository.save(taskEntity);
    }

    @Transactional
    public void deleteTaskById(long id) {// todo используй лучше Long-обертку. Тебе по сути она сюда и передается, но кастится в примитив
        log.info("Was calling deleteTaskById. Input id: {}", id);
        taskRepository.deleteById(id);
    }
}
