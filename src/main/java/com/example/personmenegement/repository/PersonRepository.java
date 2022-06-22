package com.example.personmenegement.repository;

import com.example.personmenegement.entity.PersonEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends CrudRepository<PersonEntity, Long> {

    PersonEntity findPersonById(long id);


}
