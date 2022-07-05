package com.example.personmenegement.dao;

import com.example.personmenegement.entity.PersonEntity;
import com.example.personmenegement.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service // todo @Repository, мне кажется, так будет информативнее
@RequiredArgsConstructor
public class PersonDAO {
    private final PersonRepository personRepository;

    public PersonEntity findPersonById(Long id) {
        return personRepository.findById(id).orElseGet(() -> null); // todo можно без лямбды
    }

    @Transactional
    public Long addPerson(PersonEntity personEntity) {
        return personRepository.save(personEntity).getId();
    }

    @Transactional
    public Long updatePerson(PersonEntity personEntity) {
        return personRepository.save(personEntity).getId();
    }

    @Transactional
    public void deletePersonById(long personId) {
        personRepository.deleteById(personId);
    }

}
