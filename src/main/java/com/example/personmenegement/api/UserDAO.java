package com.example.personmenegement.api;

import com.example.personmenegement.entity.UserEntity;

public interface UserDAO {

    UserEntity addUser(UserEntity userEntity);

    boolean existenceUserName(String userName);

    boolean existenceUserEmail(String userEmail);
}
