package com.example.personmenegement.controller;

import com.example.personmenegement.dto.LoginRequest;
import com.example.personmenegement.dto.UserDto;
import com.example.personmenegement.services.AuthServiceImp;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthServiceImp personService;

    @PostMapping("/signin")
    public ResponseEntity<?> authUser(@RequestBody LoginRequest loginRequest) {
        log.info("Was calling authUser. Input loginRequest: {}", loginRequest.toString());

        return ResponseEntity.ok(personService.auth(loginRequest));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody UserDto userDto) {
        log.info("Was calling registerUser. Input signupRequest: {}", userDto.toString());

        return ResponseEntity.ok(personService.registration(userDto));
    }
}
