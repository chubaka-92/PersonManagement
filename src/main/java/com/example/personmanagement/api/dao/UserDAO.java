package com.example.personmanagement.api.dao;

import com.example.personmanagement.entity.UserEntity;

public interface UserDAO {

    UserEntity addUser(UserEntity userEntity);

    boolean existenceUserName(String userName);

    boolean existenceUserEmail(String userEmail);
}
