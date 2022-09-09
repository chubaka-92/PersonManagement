package com.example.personmanagement.api;

import com.example.personmanagement.entity.RoleEntity;

public interface RoleDAO {

    RoleEntity findRoleByName(String roleName);

    boolean existenceRoleName(String userName);
}
