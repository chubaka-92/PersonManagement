package com.example.personmenegement.services.mapper;

import com.example.personmenegement.api.MessageService;
import com.example.personmenegement.api.PersonMapper;
import com.example.personmenegement.api.TaskMapper;
import com.example.personmenegement.dto.PersonDto;
import com.example.personmenegement.dto.TaskDto;
import com.example.personmenegement.entity.PersonEntity;
import com.example.personmenegement.entity.TaskEntity;
import com.example.personmenegement.types.Position;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class PersonMapperImp implements PersonMapper {

    private final MessageService messageService;
    private final TaskMapper taskMapper;

    public PersonDto personEntityToPerson(PersonEntity personEntity) {
        log.info("Was calling personEntityToPerson. Input personEntity: {}", personEntity);
        return PersonDto.builder()
                .id(getId(personEntity))
                .uid(personEntity.getUid())
                .name(personEntity.getName())
                .age(personEntity.getAge().toString())
                .email(personEntity.getEmail())
                .salary(personEntity.getSalary().toString())
                .position(messageService.getMessage(personEntity.getPosition()))
                .experience(personEntity.getExperience().toString())
                .tasks(getTasks(personEntity.getTasks()))
                .build();
    }

    public PersonEntity personToPersonEntity(PersonDto personDto) {
        log.info("Was calling personToPersonEntity. Input id: {}", personDto);
        return PersonEntity.builder()
                .id(getId(personDto))
                .uid(getUid(personDto))
                .name(personDto.getName())
                .age(Integer.valueOf(personDto.getAge()))
                .email(personDto.getEmail())
                .salary(new BigDecimal(personDto.getSalary()))
                .position(Position.definePosition(personDto.getPosition()))
                .experience(Double.valueOf(personDto.getExperience()))
                .build();
    }

    private Long getId(PersonDto personDto) {
        log.debug("Was calling getId.");
        if (personDto.getId() == null) {
            return null;
        }
        return Long.valueOf(personDto.getId());
    }

    private String getId(PersonEntity personEntity) {
        log.debug("Was calling getId.");
        if (personEntity.getId() == null) {
            return null;
        }
        return personEntity.getId().toString();
    }

    private List<TaskDto> getTasks(List<TaskEntity> taskEntities) {
        log.debug("Was calling getTasks.");
        if (taskEntities == null) {
            return null;
        }
        return taskEntities
                .stream()
                .map(taskMapper::taskEntityToTask)
                .collect(Collectors.toList());
    }

    private String getUid(PersonDto personDto) {
        log.debug("Was calling getUid. Input personDto: {}", personDto);
        if (personDto.getUid() == null) {
            return UUID.randomUUID().toString();
        }
        return personDto.getUid();
    }
}
