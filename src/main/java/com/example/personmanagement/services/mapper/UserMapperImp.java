package com.example.personmanagement.services.mapper;

import com.example.personmanagement.dao.RoleDAOImp;
import com.example.personmanagement.dto.UserDto;
import com.example.personmanagement.entity.RoleEntity;
import com.example.personmanagement.entity.UserEntity;
import com.example.personmanagement.types.Roles;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserMapperImp {

    private final PasswordEncoder passwordEncoder;
    private final RoleDAOImp roleDAOImp;

    public UserDto userEntityToUserDto(UserEntity userEntity) {
        log.info("Was calling userEntityToUserDto. Input userEntity: {}", userEntity);
        return UserDto.builder()
                .username(userEntity.getUsername())
                .email(userEntity.getEmail())
                .roles(getRoles(userEntity.getRoleEntities()))
                .build();
    }

    public UserEntity userDtoToUserEntity(UserDto userDto) {
        log.info("Was calling userDtoToUserEntity. Input userDto: {}", userDto);
        return UserEntity.builder()
                .username(userDto.getUsername())
                .email(userDto.getEmail())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .roleEntities(getEntityRoles(userDto.getRoles()))
                .build();

    }

    private Set<RoleEntity> getEntityRoles(Set<String> roles) {
        log.debug("Was calling getEntityRoles. Input roles: {}", roles);
        return roles.stream()
                .map(role -> roleDAOImp.findRoleByName(Roles.defineRole(role).name()))
                .collect(Collectors.toSet());
    }

    private Set<String> getRoles(Set<RoleEntity> roleEntities) {
        log.debug("Was calling getRoles. Input roleEntities: {}", roleEntities);
        return roleEntities.stream()
                .map(roleEntity -> roleEntity.getName().getName())
                .collect(Collectors.toSet());
    }

}
