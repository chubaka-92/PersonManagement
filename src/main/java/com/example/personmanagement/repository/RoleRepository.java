package com.example.personmanagement.repository;

import com.example.personmanagement.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {

    @Query(value = "SELECT r.* FROM roles r WHERE r.name = ?1", nativeQuery = true)
    Optional<RoleEntity> findByName(String name);

    @Query(value = "SELECT COUNT(r)>0 FROM roles r WHERE r.name = ?1", nativeQuery = true)
    Boolean existsByName(String name);
}
