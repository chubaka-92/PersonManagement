package com.example.personmanagement.controller;

import com.example.personmanagement.dto.LoginRequest;
import com.example.personmanagement.dto.UserDto;
import com.example.personmanagement.services.AuthServiceImp;
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
        log.info("Was calling authUser. Input loginRequest: {}", loginRequest);
        return ResponseEntity.ok(personService.auth(loginRequest));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody UserDto userDto) {
        log.info("Was calling registerUser. Input userDto: {}", userDto);
        return ResponseEntity.ok(personService.registration(userDto));
    }
}
