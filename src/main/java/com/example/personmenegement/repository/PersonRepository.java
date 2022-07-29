package com.example.personmenegement.repository;

import com.example.personmenegement.entity.PersonEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface PersonRepository extends CrudRepository<PersonEntity, Long> {
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM person p WHERE p.id = (SELECT min(pe.id) FROM person pe)", nativeQuery = true)
    void deletePersonOldTask();
}
