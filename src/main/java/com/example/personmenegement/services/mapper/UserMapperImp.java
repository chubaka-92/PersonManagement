package com.example.personmenegement.services.mapper;

import com.example.personmenegement.dto.UserDto;
import com.example.personmenegement.entity.RoleEntity;
import com.example.personmenegement.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserMapperImp {

    public UserDto userEntityToUserDto(UserEntity userEntity) {
        log.info("Was calling userEntityToUserDto. Input userEntity: {}", userEntity);
        return UserDto.builder()
                .username(userEntity.getUsername())
                .email(userEntity.getEmail())
                .roles(userEntity.getRoleEntities())
                .build();
    }

    public UserEntity userDtoToUserEntity(UserDto userDto) {
        log.info("Was calling userDtoToUserEntity. Input userDto: {}", userDto);
        return UserEntity.builder()
                .username(userDto.getUsername())
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .roleEntities(userDto.getRoles())
                .build();
    }
    private List<RoleEntity> getRoles(List<String> roles) {
        log.debug("Was calling getTasks.");
        if (roles == null) {
            return null;
        }
        return roles
                .stream()
                .map(taskMapper::taskEntityToTask)
                .collect(Collectors.toList());
    }

}
