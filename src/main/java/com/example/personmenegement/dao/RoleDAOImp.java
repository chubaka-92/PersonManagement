package com.example.personmenegement.dao;

import com.example.personmenegement.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
@RequiredArgsConstructor
public class RoleDAOImp {
    private final RoleRepository roleRepository;


}
