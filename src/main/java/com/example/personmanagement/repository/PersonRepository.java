package com.example.personmanagement.repository;

import com.example.personmanagement.entity.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface PersonRepository extends JpaRepository<PersonEntity, Long> {

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM person p WHERE p.id = (SELECT min(pe.id) FROM person pe)", nativeQuery = true)
    void deletePersonOldTask();

    Optional<PersonEntity> findByUid(String uid);
}
