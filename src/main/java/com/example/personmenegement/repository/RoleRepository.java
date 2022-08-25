package com.example.personmenegement.repository;

import com.example.personmenegement.entity.RoleEntity;
import com.example.personmenegement.types.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
    Optional<RoleEntity> findByName(Roles name);
}
