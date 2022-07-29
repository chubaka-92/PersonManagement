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
    public ResponseEntity getTask(@PathVariable("id") Long id) {
        log.info("Was calling getTask. Input id: " + id);
        return taskService.getTaskById(Long.valueOf(id));
    }

    @GetMapping()
    public ResponseEntity getTasks() {
        log.info("Was calling getTasks.");
        return taskService.getTasks();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteTask(@PathVariable("id") Long id) {
        log.info("Was calling deleteTask. Input id: " + id);
        return taskService.deleteTask(Long.valueOf(id));
    }

    @PostMapping("/task")
    public ResponseEntity createTask(@RequestBody Task task, @RequestParam Long personId) {
        log.info("Was calling createTask. Input task: " + task.toString(), " personId: " + personId);
        return taskService.addNewTask(task, personId);
    }

    @PostMapping("")
    public ResponseEntity createTasks(@RequestBody List<Task> tasks, @RequestParam Long personId) {
        log.info("Was calling createTasks. Input tasks: " + tasks.toString());
        return taskService.addNewTasks(tasks, personId);
    }

    @PutMapping()
    public ResponseEntity updateTask(@RequestBody Task task) {
        log.info("Was calling updateTask. Input task: " + task.toString());
        return taskService.updateTask(task);
    }
}
