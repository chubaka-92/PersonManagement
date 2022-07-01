package com.example.personmenegement.repository;

import com.example.personmenegement.entity.PersonEntity;
import com.example.personmenegement.soap.person.Person;
import org.springframework.data.repository.CrudRepository;

//todo аннотация не нужна
// done
public interface PersonRepository extends CrudRepository<PersonEntity, Long> {
    // todo у CrudRepository есть уже метод findById
    //  done. получается сюда мы пишим только кастомные методы, которых нет в CrudRepository

}
