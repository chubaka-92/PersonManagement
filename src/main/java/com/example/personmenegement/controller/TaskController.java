package com.example.personmenegement.controller;

import com.example.personmenegement.api.TaskService;
import com.example.personmenegement.dto.TaskDto;
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

    private final TaskService taskService;

    @GetMapping("/{uid}")
    public ResponseEntity<TaskDto> getTask(@PathVariable("uid") String uid) {// todo ResponseEntity смотри PersonController  //   DONE
        log.info("Was calling getTask. Input uid: {}", uid);
        TaskDto taskResponse =taskService.getTaskByUid(uid);
        return ResponseEntity.ok(taskResponse);// todo зачем valueOf если и так приходит Long? убрать // DONE
    }

    @GetMapping()
    public ResponseEntity<List<TaskDto>> getTasks() {// todo ResponseEntity смотри PersonController  //   DONE
        log.info("Was calling getTasks.");
        return ResponseEntity.ok(taskService.getTasks());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteTask(@PathVariable("id") Long id) {// todo ResponseEntity смотри PersonController  //   DONE
        log.info("Was calling deleteTask. Input id: {}", id);
        return ResponseEntity.ok(taskService.deleteTask(id));// todo зачем valueOf если и так приходит Long? убрать  // DONE
    }

    @PostMapping("/task/person/{id}")
    public ResponseEntity<TaskDto> createTask(@RequestBody TaskDto taskDto, @PathVariable("id") Long personId) {// todo ResponseEntity смотри PersonController + personId лучше в PathVariable  //   DONE
        log.info("Was calling createTask. Input task: {} personId: {}", taskDto, personId);
        return ResponseEntity.ok(taskService.addNewTask(taskDto, personId));
    }

    @PostMapping("/person/{id}")
    public ResponseEntity<List<TaskDto>> createTasks(@RequestBody List<TaskDto> tasksDto, @PathVariable("id") Long personId) {// todo ResponseEntity смотри PersonController  //   DONE
        log.info("Was calling createTasks. Input tasks: {} personId: {}", tasksDto.toString(), personId);
        return ResponseEntity.ok(taskService.addNewTasks(tasksDto, personId));
    }

    @PutMapping("/task/person/{id}")
    public ResponseEntity<TaskDto> updateTask(@RequestBody TaskDto taskDto, @PathVariable("id") Long personId) {// todo ResponseEntity смотри PersonController  //   DONE
        log.info("Was calling updateTask. Input task: {} personId: {}", taskDto, personId);
        return ResponseEntity.ok(taskService.updateTask(taskDto, personId));
    }
}
