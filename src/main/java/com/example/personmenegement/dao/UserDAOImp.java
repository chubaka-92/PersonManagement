package com.example.personmenegement.dao;

import com.example.personmenegement.api.UserDAO;
import com.example.personmenegement.entity.UserEntity;
import com.example.personmenegement.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class UserDAOImp implements UserDAO {
    private final UserRepository userRepository;

    @Transactional
    public UserEntity addUser(UserEntity userEntity) {
        log.info("Was calling addUser. Input userEntity: {}", userEntity);
        return userRepository.save(userEntity);
    }

    public boolean existenceUserName(String userName) {
        log.info("Was calling existenceUserName. Input userName: {}", userName);
        return userRepository.existsByUsername(userName);
    }

    public boolean existenceUserEmail(String userEmail) {
        log.info("Was calling existenceUserEmail. Input userEmail: {}", userEmail);
        return userRepository.existsByEmail(userEmail);
    }
}
