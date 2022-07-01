package com.example.personmenegement.dao;

import com.example.personmenegement.entity.PersonEntity;
import com.example.personmenegement.repository.PersonMapper;
import com.example.personmenegement.repository.PersonRepository;
import com.example.personmenegement.soap.person.GetPersonByIdResponse;
import com.example.personmenegement.soap.person.Person;
import com.example.personmenegement.soap.person.ServiceStatus;
import com.example.personmenegement.types.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Optional;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class PersonDAO {
    private final PersonRepository personRepository;

    public PersonEntity findPersonById(Long id) {
        return personRepository.findById(id).orElseGet(() -> null);
    }

    // todo @Transactional
    //  done
    @Transactional
    public Long addPerson(PersonEntity personEntity) {
        return personRepository.save(personEntity).getId();
    }

    // todo @Transactional
    //  done
    @Transactional
    public Long updatePerson(PersonEntity personEntity) {
        return personRepository.save(personEntity).getId();
    }

    // todo @Transactional
    //  done
    @Transactional
    public void deletePersonById(long personId) {
        personRepository.deleteById(personId);
    }
}
