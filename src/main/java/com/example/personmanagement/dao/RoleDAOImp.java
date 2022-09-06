package com.example.personmanagement.dao;

import com.example.personmanagement.api.RoleDAO;
import com.example.personmanagement.entity.RoleEntity;
import com.example.personmanagement.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
@RequiredArgsConstructor
public class RoleDAOImp implements RoleDAO {
    private final RoleRepository roleRepository;

    @Override
    public RoleEntity findRoleByName(String roleName) {
        log.info("Was calling findRoleByName. Input roleName: {}", roleName);
        return roleRepository.findByName(roleName).orElse(null);
    }

    public boolean existenceRoleName(String userName) {
        log.info("Was calling existenceRoleName. Input userName: {}", userName);
        return roleRepository.existsByName(userName);
    }

}
