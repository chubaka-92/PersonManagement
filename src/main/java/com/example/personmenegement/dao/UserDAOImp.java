package com.example.personmenegement.dao;

import com.example.personmenegement.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
@RequiredArgsConstructor
public class UserDAOImp {
    private final UserRepository userRepository;


}
