package com.example.personmenegement.services;

import com.example.personmenegement.config.securety.jwt.JwtUtils;
import com.example.personmenegement.dao.UserDAOImp;
import com.example.personmenegement.dto.JwtResponse;
import com.example.personmenegement.dto.LoginRequest;
import com.example.personmenegement.dto.UserDto;
import com.example.personmenegement.entity.RoleEntity;
import com.example.personmenegement.entity.UserEntity;
import com.example.personmenegement.repository.RoleRepository;
import com.example.personmenegement.repository.UserRepository;
import com.example.personmenegement.services.mapper.UserMapperImp;
import com.example.personmenegement.services.security.UserDetailsImpl;
import com.example.personmenegement.types.Roles;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImp {

    private final UserDAOImp userDAO;
    private final UserMapperImp personMapper;
    AuthenticationManager authenticationManager;


    UserRepository userRepository;


    RoleRepository roleRepository;


    PasswordEncoder passwordEncoder;


    JwtUtils jwtUtils;

    public Object auth(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles);
    }

    public Object registration(UserDto userDto) {
        if (userRepository.existsByUsername(userDto.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is exist"));
        }

        if (userRepository.existsByEmail(userDto.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is exist"));
        }

        UserEntity user = new UserEntity(userDto.getUsername(),
                userDto.getEmail(),
                passwordEncoder.encode(userDto.getPassword()));

        Set<String> reqRoles = userDto.getRoles();
        Set<RoleEntity> roleEntities = new HashSet<>();

        if (reqRoles == null) {
            RoleEntity userRoleEntity = roleRepository
                    .findByName(Roles.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error, Role USER is not found"));
            roleEntities.add(userRoleEntity);
        } else {
            reqRoles.forEach(r -> {
                switch (r) {
                    case "admin":
                        RoleEntity adminRoleEntity = roleRepository
                                .findByName(Roles.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error, Role ADMIN is not found"));
                        roleEntities.add(adminRoleEntity);

                        break;
                    case "mod":
                        RoleEntity modRoleEntity = roleRepository
                                .findByName(Roles.ROLE_MODERATOR)
                                .orElseThrow(() -> new RuntimeException("Error, Role MODERATOR is not found"));
                        roleEntities.add(modRoleEntity);

                        break;

                    default:
                        RoleEntity userRoleEntity = roleRepository
                                .findByName(Roles.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error, Role USER is not found"));
                        roleEntities.add(userRoleEntity);
                }
            });
        }
        user.setRoleEntities(roleEntities);
        userRepository.save(user);
        return "User CREATED";
    }
}
