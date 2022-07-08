package com.example.personmenegement.dao;

import com.example.personmenegement.entity.PersonEntity;
import com.example.personmenegement.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

// todo @Repository, мне кажется, так будет информативнее
//   done
@Repository
@RequiredArgsConstructor
public class PersonDAO {
    private final PersonRepository personRepository;

    public PersonEntity findPersonById(Long id) {
        return personRepository.findById(id).orElse(null); // todo можно без лямбды    //DONE
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
