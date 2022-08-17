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
        List<TaskDto> response = new ArrayList<>();
        for (TaskDto taskDto : tasksDto) {
            TaskDto taskDtoTemp = taskValidation.validate(taskDto);
            if (taskDtoTemp == null) {
                response.add(getNewTask(personEntity, taskDto));
            } else {
                log.error(taskDto.toString());
                response.add(taskDto);
            }
        }
        return response;
    }

    public TaskDto updateTask(TaskDto taskDto, Long personId) {
        log.info("Was calling updateTask. Input task: {} personId: {}", taskDto, personId);
        PersonEntity personEntity = personDao.findPersonById(personId);
        if (personEntity == null) {
            log.error(MessageFormat.format(messageService.getMessage(PERSON_NOT_FOUND), personId));
            throw new PersonNotFoundException(MessageFormat.format(messageService.getMessage(PERSON_NOT_FOUND), personId));
        }
        TaskDto taskDtoTemp = taskValidation.validate(taskDto);
        if (!(taskDtoTemp == null)) {
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
                countTasks); //todo вот так опрятнее выглядит. не нравится использование "+" в логах (много места занимает и выглядит не оч). Везде где есть вставка значений в логи сделать такой вид. + желательно, делать лог в одну строку, но если не получается, то сделать, как здесь
        //  done
        if (personEntity.getTasks().size() < personEntity.getPosition().getCountTasks()
                && getCountAvailableTasks(personEntity) >= countTasks) {
            return false;
        }
        return true;
    }
}
