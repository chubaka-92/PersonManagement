package com.example.personmenegement.services.mapper;

import com.example.personmenegement.api.PersonMapper;
import com.example.personmenegement.entity.PersonEntity;
import com.example.personmenegement.soap.person.Person;
import com.example.personmenegement.types.Position;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class PersonMapperImp implements PersonMapper {

    public Person personEntityToPerson(PersonEntity personEntity) {
        return Person.builder()
                .id(String.valueOf(personEntity.getId()))
                .name(personEntity.getName())
                .age(String.valueOf(personEntity.getAge()))
                .email(personEntity.getEmail())
                .salary(String.valueOf(personEntity.getSalary()))
                .position(personEntity.getPosition().toString())
                .valid(true)
                .experience(personEntity.getExperience().toString())
                .build();
    }

    public PersonEntity personToPersonEntity(Person person) {
        return PersonEntity.builder()
                .id(getId(person))
                .name(person.getName())
                .age(Integer.valueOf(person.getAge()))
                .email(person.getEmail())
                .salary(new BigDecimal(person.getSalary()))
                .position(Position.getDefine(person.getPosition()))
                .experience(Double.valueOf(person.getExperience()))
                .build();
    }

    private Long getId(Person person) {
        if (person.getId() == null) {
            return null;
        }
        return Long.valueOf(person.getId());
    }
}
