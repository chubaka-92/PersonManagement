package com.example.personmenegement.repository;

import com.example.personmenegement.entity.PersonEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

//todo аннотация не нужна
// done
public interface PersonRepository extends CrudRepository<PersonEntity, Long> {
    // todo у CrudRepository есть уже метод findById
    //  done. получается сюда мы пишим только кастомные методы, которых нет в CrudRepository

}
