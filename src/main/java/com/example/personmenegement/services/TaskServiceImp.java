package com.example.personmenegement.services;

import com.example.personmenegement.api.*;
import com.example.personmenegement.dao.TaskDAOImp;
import com.example.personmenegement.dto.TaskDto;
import com.example.personmenegement.entity.PersonEntity;
import com.example.personmenegement.entity.TaskEntity;
import com.example.personmenegement.exeption.ManyTasksException;
import com.example.personmenegement.exeption.PersonNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class TaskServiceImp implements TaskService {

    private static final int ONE_TASK = 1;
    private final TaskDAOImp taskDAO;
    private static final String TOO_MANY_TASKS = "tooManyTasks";
    private static final String TASK_NOT_FOUND = "taskNotFound";
    private static final String TASKS_NOT_FOUND = "tasksNotFound";
    private static final String PERSON_NOT_FOUND = "personNotFound";
    private final MessageService messageService;
    private final TaskMapper taskMapper;
    private final TaskValidation taskValidation;
    private final PersonDAO personDao;
    private final TaskProducer taskProducer;


    @Override
    public TaskDto getTaskByUid(String uid) {
        log.info("Was calling getTaskByUid. Input id: {}", uid);
        TaskEntity taskEntity = taskDAO.findTaskByUid(uid);
        return taskMapper.taskEntityToTask(taskEntity);
    }

    public List<TaskDto> getTasks() {
        log.info("Was calling getTasks.");
        List<TaskEntity> tasks = taskDAO.findTasks();
        return tasks.stream()
                .map(taskMapper::taskEntityToTask)
                .collect(Collectors.toList());
    }

    public Long deleteTask(Long id) {
        log.info("Was calling deleteTask. Input id: {}", id);
        taskDAO.deleteTaskById(id);
        return id;
    }

    public TaskDto addNewTask(TaskDto taskDto, Long personId) {
        log.info("Was calling addNewTask. Input task: {} personId: {}", taskDto, personId);
        PersonEntity personEntity = personDao.findPersonById(personId);
        if (checkAvailableCountTasksToPerson(ONE_TASK, personEntity)) {
            log.error(MessageFormat.format(messageService.getMessage(TOO_MANY_TASKS), getCountAvailableTasks(personEntity)));
            throw new ManyTasksException(MessageFormat.format(messageService.getMessage(TOO_MANY_TASKS), getCountAvailableTasks(personEntity)));
        }
        TaskDto taskDtoTemp = taskValidation.validate(taskDto);
        if (taskDtoTemp != null) {
            log.error(taskDtoTemp.toString());
            return taskDtoTemp;
        }
        return getNewTask(personEntity, taskDto);
    }

    @Override
    public List<TaskDto> addNewTasks(List<TaskDto> tasksDto, Long personId) {
        log.info("Was calling addNewTasks. Input tasks: {} personId: {}", tasksDto, personId);
        PersonEntity personEntity = personDao.findPersonById(personId);

        if (checkAvailableCountTasksToPerson(tasksDto.size(), personEntity)) {
            log.error(MessageFormat.format(messageService.getMessage(TOO_MANY_TASKS), getCountAvailableTasks(personEntity)));
            throw new ManyTasksException(MessageFormat.format(messageService.getMessage(TOO_MANY_TASKS), getCountAvailableTasks(personEntity)));
        }
        // todo используй стримы
        //  Done
        return tasksDto.stream()
                .map(taskDto -> getTaskDto(taskDto,personEntity))
                .collect(Collectors.toList());
    }

    private TaskDto getTaskDto(TaskDto taskDto,PersonEntity personEntity) {
        log.info("Was calling getTaskDto. Input taskDto: {} personId: {}", taskDto, personEntity);
        TaskDto result = taskValidation.validate(taskDto);
        if (result == null) {
            return getNewTask(personEntity,taskDto);
        }
        return result;
    }

    public TaskDto updateTask(TaskDto taskDto, Long personId) {
        log.info("Was calling updateTask. Input task: {} personId: {}", taskDto, personId);
        PersonEntity personEntity = personDao.findPersonById(personId);
        if (personEntity == null) {
            log.error(MessageFormat.format(messageService.getMessage(PERSON_NOT_FOUND), personId));
            throw new PersonNotFoundException(MessageFormat.format(messageService.getMessage(PERSON_NOT_FOUND), personId));
        }
        TaskDto taskDtoTemp = taskValidation.validate(taskDto);
        if (taskDtoTemp != null) {// todo сложно. Лучше например так: if (taskDto != null)  //  Done
            log.error(taskDtoTemp.toString());
            return taskDtoTemp;
        }
        return getUpdateTask(personEntity, taskDto);
    }

    private TaskDto getNewTask(PersonEntity personEntity, TaskDto taskDto) {
        log.debug("Was calling addNewTasks. Input personEntity: {} taskTemp: {}", personEntity, taskDto);
        TaskEntity taskEntity = taskMapper.taskToTaskEntity(taskDto);
        taskEntity.setPersonId(personEntity.getId());
        taskProducer.sendTask(taskEntity);
        return taskMapper.taskEntityToTask(taskEntity);
    }

    private TaskDto getUpdateTask(PersonEntity personEntity, TaskDto taskDto) {
        log.debug("Was calling getUpdateTask. Input personEntity: {} taskTemp: {}", personEntity, taskDto);
        TaskEntity taskEntity = taskMapper.taskToTaskEntity(taskDto);
        taskEntity.setPersonId(personEntity.getId());
        taskEntity.setId(taskDAO.updateTask(taskEntity).getId());
        return taskMapper.taskEntityToTask(taskEntity);
    }

    private static Integer getCountAvailableTasks(PersonEntity personEntity) {
        return personEntity.getPosition().getCountTasks() - personEntity.getTasks().size();
    }

    private boolean checkAvailableCountTasksToPerson(int countTasks, PersonEntity personEntity) {
        log.info("Was calling checkAvailableCountTasksToPerson. Input personEntity: {} countTasks: {}",
                personEntity,
                countTasks);
        if (personEntity.getTasks().size() < personEntity.getPosition().getCountTasks()
                && getCountAvailableTasks(personEntity) >= countTasks) {
            return false;
        }
        return true;
    }
}
