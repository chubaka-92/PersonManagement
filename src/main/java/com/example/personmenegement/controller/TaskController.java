package com.example.personmenegement.controller;

import com.example.personmenegement.dto.Task;
import com.example.personmenegement.services.TaskServiceImp;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/tasks")
public class TaskController {

    private final TaskServiceImp taskService;

    @GetMapping("/{id}")
    public ResponseEntity getTask(@PathVariable("id") Long id) {// todo ResponseEntity смотри PersonController
        log.info("Was calling getTask. Input id: {}", id);
        return taskService.getTaskById(Long.valueOf(id));// todo зачем valueOf если и так приходит Long? убрать
    }

    @GetMapping()
    public ResponseEntity getTasks() {// todo ResponseEntity смотри PersonController
        log.info("Was calling getTasks.");
        return taskService.getTasks();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteTask(@PathVariable("id") Long id) {// todo ResponseEntity смотри PersonController
        log.info("Was calling deleteTask. Input id: {}", id);
        return taskService.deleteTask(Long.valueOf(id));// todo зачем valueOf если и так приходит Long? убрать
    }

    @PostMapping("/task")
    public ResponseEntity createTask(@RequestBody Task task, @RequestParam Long personId) {// todo ResponseEntity смотри PersonController + personId лучше в PathVariable
        log.info("Was calling createTask. Input task: {} personId: {}", task,  personId);
        return taskService.addNewTask(task, personId);
    }

    @PostMapping("")
    public ResponseEntity createTasks(@RequestBody List<Task> tasks, @RequestParam Long personId) {// todo ResponseEntity смотри PersonController
        log.info("Was calling createTasks. Input tasks: {} personId: {}", tasks.toString(),personId);
        return taskService.addNewTasks(tasks, personId);
    }

    @PutMapping()
    public ResponseEntity updateTask(@RequestBody Task task) {// todo ResponseEntity смотри PersonController
        log.info("Was calling updateTask. Input task: {}" + task.toString());
        return taskService.updateTask(task);
    }
}
