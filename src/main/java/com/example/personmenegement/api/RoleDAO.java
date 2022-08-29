package com.example.personmenegement.api;

import com.example.personmenegement.entity.RoleEntity;

public interface RoleDAO {

    RoleEntity findRoleByName(String roleName);

    boolean existenceRoleName(String userName);
}
