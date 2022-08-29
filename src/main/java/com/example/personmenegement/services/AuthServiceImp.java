package com.example.personmenegement.services;

import com.example.personmenegement.api.UserValidation;
import com.example.personmenegement.config.securety.jwt.JwtUtils;
import com.example.personmenegement.dao.UserDAOImp;
import com.example.personmenegement.dto.JwtResponse;
import com.example.personmenegement.dto.LoginRequest;
import com.example.personmenegement.dto.UserDto;
import com.example.personmenegement.entity.UserEntity;
import com.example.personmenegement.repository.RoleRepository;
import com.example.personmenegement.services.mapper.UserMapperImp;
import com.example.personmenegement.services.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImp {

    private static final String AUTH_TYPE = "Bearer";
    private final UserDAOImp userDAO;
    private final UserValidation userValidation;
    private final AuthenticationManager authenticationManager;
    private final UserMapperImp userMapper;
    private final JwtUtils jwtUtils;

    public JwtResponse auth(LoginRequest loginRequest) {
        log.info("Was calling auth. Input loginRequest: {}", loginRequest);
        Authentication authentication = getAuthenticate(loginRequest);

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return JwtResponse.builder()
                .id(userDetails.getId())
                .username(userDetails.getUsername())
                .email(userDetails.getEmail())
                .token(jwt)
                .type(AUTH_TYPE)
                .roles(roles)
                .build();
    }

    public UserDto registration(UserDto userDto) {
        log.info("Was calling registration. Input userDto: {}", userDto);
        UserDto userResponse = userValidation.validate(userDto);
        if (userResponse != null) {
            log.error(userResponse.toString());
            return userResponse;
        }
        UserEntity user = userMapper.userDtoToUserEntity(userDto);
        return userMapper.userEntityToUserDto(userDAO.addUser(user));
    }

    private Authentication getAuthenticate(LoginRequest loginRequest) {
        log.debug("Was calling getAuthenticate. Input loginRequest: {}", loginRequest);
        return authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()));
    }
}
