package com.example.personmenegement.services.mapper;

import com.example.personmenegement.api.MessageService;
import com.example.personmenegement.api.PersonMapper;
import com.example.personmenegement.api.TaskMapper;
import com.example.personmenegement.dto.Person;
import com.example.personmenegement.dto.Task;
import com.example.personmenegement.entity.PersonEntity;
import com.example.personmenegement.entity.TaskEntity;
import com.example.personmenegement.types.Position;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class PersonMapperImp implements PersonMapper {

    private final MessageService messageService;
    private final TaskMapper taskMapper;

    public Person personEntityToPerson(PersonEntity personEntity) {
        log.info("Was calling personEntityToPerson. Input personEntity: {}", personEntity.toString());
        return Person.builder()
                .id(String.valueOf(personEntity.getId()))
                .name(personEntity.getName())
                .age(String.valueOf(personEntity.getAge()))
                .email(personEntity.getEmail())
                .salary(String.valueOf(personEntity.getSalary()))
                .position(messageService.getMessage(personEntity.getPosition()))
                .experience(personEntity.getExperience().toString())
                .tasks(getTasks(personEntity.getTasks()))
                .build();
    }

    public PersonEntity personToPersonEntity(Person person) {
        log.info("Was calling personToPersonEntity. Input id: {}", person.toString());
        return PersonEntity.builder()
                .id(getId(person))
                .name(person.getName())
                .age(Integer.valueOf(person.getAge()))
                .email(person.getEmail())
                .salary(new BigDecimal(person.getSalary()))
                .position(Position.definePosition(person.getPosition()))
                .experience(Double.valueOf(person.getExperience()))
                .build();
    }

    private Long getId(Person person) {
        log.debug("Was calling getId.");
        if (person.getId() == null) {
            return null;
        }
        return Long.valueOf(person.getId());
    }

    private List<Task> getTasks(List<TaskEntity> taskEntities) {
        log.debug("Was calling getTasks.");
        if (taskEntities == null) {
            return null;
        }
        return taskEntities
                .stream()
                .map(taskMapper::taskEntityToTask)
                .collect(Collectors.toList());
    }
}
