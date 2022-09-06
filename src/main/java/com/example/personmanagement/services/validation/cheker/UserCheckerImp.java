package com.example.personmanagement.services.validation.cheker;

import com.example.personmanagement.api.RoleDAO;
import com.example.personmanagement.api.UserChecker;
import com.example.personmanagement.api.UserDAO;
import com.example.personmanagement.dto.UserDto;
import com.example.personmanagement.services.MessageService;
import com.example.personmanagement.types.Roles;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.util.*;

import static com.example.personmanagement.types.UserFieldName.*;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserCheckerImp implements UserChecker {
    private static final String USER_NAME_EXIST = "userNameExist";
    private static final String INCORRECT_ROLE = "incorrectRole";
    private static final String ROLE_NOT_FOUND = "roleNotFound";
    private static final String EMAIL_EXIST = "emailExist";
    private final MessageService messageService = new MessageService();
    private final UserDAO userDAO;
    private final RoleDAO roleDAO;

    @Override
    public List<String> checkRequiredFields(UserDto userDto) {
        log.info("Was calling checkRequiredFields. Input userDto: {}", userDto);
        List<String> invalidFields = new ArrayList<>();
        if (userDto.getUsername() == null || userDto.getUsername().isBlank()) {
            invalidFields.add(USER_NAME);
        }
        if (userDto.getEmail() == null || userDto.getEmail().isBlank()) {
            invalidFields.add(EMAIL);
        }
        if (userDto.getPassword() == null || userDto.getPassword().isBlank()) {
            invalidFields.add(PASSWORD);
        }
        if (userDto.getRoles() == null || userDto.getRoles().isEmpty()) {
            invalidFields.add(ROLES);
        }

        return invalidFields;
    }

    @Override
    public Map<String, String> checkUserName(String username) {
        log.info("Was calling checkUserName. Input username: {}", username);
        Map<String, String> response = new HashMap<>();
        if (userDAO.existenceUserName(username)) {
            String message = messageService.getMessage(USER_NAME_EXIST);
            response.put(USER_NAME, message);
        }
        return response;
    }

    @Override
    public Map<String, String> checkEmail(String email) {
        log.info("Was calling checkEmail. Input email: {}", email);
        Map<String, String> response = new HashMap<>();
        if (userDAO.existenceUserEmail(email)) {
            String message = messageService.getMessage(EMAIL_EXIST);
            response.put(EMAIL, message);
        }
        return response;
    }

    @Override
    public Map<String, String> checkRoles(Set<String> roles) {
        log.info("Was calling checkRoles. Input roles: {}", roles);
        Map<String, String> response = new HashMap<>();
        roles.forEach(role -> checkingRole(response, role));
        return response;
    }

    private void checkingRole(Map<String, String> response, String role) {
        log.debug("Was calling checkingRole. Input response: {} role: {}", response, role);
        Roles roleUser = Roles.defineRole(role);
        if (roleUser == Roles.UNDEFINED) {
            String message = MessageFormat.format(messageService.getMessage(INCORRECT_ROLE), role);
            response.put(ROLES, message);
        } else if (!roleDAO.existenceRoleName(roleUser.name())) {
            String message = MessageFormat.format(messageService.getMessage(ROLE_NOT_FOUND), role);
            response.put(ROLES, message);
        }
    }
}
