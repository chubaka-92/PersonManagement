package com.example.personmenegement.repository;
import com.example.personmenegement.entity.PersonEntity;
import com.example.personmenegement.soap.person.Person;
import com.example.personmenegement.types.Position;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
// todo unused imports
import java.util.function.Function;

@Service
public class PersonMapper { // todo в отдельный пакет mapper

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
                .id(!(person.getId()==(null))?Long.valueOf(person.getId()):null) // todo вынеси логику в отдельный метод
                .name(person.getName())
                .age(Integer.valueOf(person.getAge()))
                .email(person.getEmail())
                .salary(new BigDecimal(person.getSalary()))
                .position(Position.valueOf(person.getPosition()))
                .experience(Double.valueOf(person.getExperience()))
                .build();
    }
}
