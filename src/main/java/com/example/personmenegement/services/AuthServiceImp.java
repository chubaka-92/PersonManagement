package com.example.personmenegement.services;

import com.example.personmenegement.config.securety.jwt.JwtUtils;
import com.example.personmenegement.dao.UserDAOImp;
import com.example.personmenegement.dto.JwtResponse;
import com.example.personmenegement.dto.LoginRequest;
import com.example.personmenegement.dto.UserDto;
import com.example.personmenegement.entity.RoleEntity;
import com.example.personmenegement.entity.UserEntity;
import com.example.personmenegement.exeption.UserEmailExistAuthException;
import com.example.personmenegement.exeption.UserNameExistAuthException;
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

    private final AuthenticationManager authenticationManager;

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapperImp userMapper;
    private final JwtUtils jwtUtils;

    public Object auth(LoginRequest loginRequest) {
        Authentication authentication = getAuthenticate(loginRequest);

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

    private Authentication getAuthenticate(LoginRequest loginRequest) {
        return authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()));
    }

    public Object registration(UserDto userDto) {
        if (userRepository.existsByUsername(userDto.getUsername())) {
            throw new UserNameExistAuthException("Error: Username is exist");
        }

        if (userRepository.existsByEmail(userDto.getEmail())) {
            throw new UserEmailExistAuthException("Error: Email is exist");
        }

        UserEntity user = userMapper.userDtoToUserEntity(userDto);

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
