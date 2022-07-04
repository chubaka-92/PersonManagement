package com.example.personmenegement.repository;

import com.example.personmenegement.entity.PersonEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

//todo аннотация не нужна
// done
public interface PersonRepository extends CrudRepository<PersonEntity, Long> {
    // todo у CrudRepository есть уже метод findById
    //  done. получается сюда мы пишим только кастомные методы, которых нет в CrudRepository

    @Query(value = "SELECT * FROM person p WHERE p.id = (SELECT min(pe.id) FROM person pe)", nativeQuery = true)
    PersonEntity findPersonOldTask();

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM person p WHERE p.id = (SELECT min(pe.id) FROM person pe)", nativeQuery = true)
    void deletePersonOldTask();
}
