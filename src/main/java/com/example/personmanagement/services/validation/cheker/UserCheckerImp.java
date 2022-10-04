package com.example.personmanagement.services.validation.cheker;

import com.example.personmanagement.api.checker.UserChecker;
import com.example.personmanagement.api.dao.RoleDAO;
import com.example.personmanagement.api.dao.UserDAO;
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
    private static final String INCORRECT_EMAIL = "incorrectEmail";
    private final MessageService messageService;
    private final UserDAO userDAO;
    private final RoleDAO roleDAO;

    @Override
    public List<String> checkRequiredFields(UserDto userDto) {
        log.info("Was calling checkRequiredFields. Input userDto: {}", userDto);
        List<String> invalidFields = new ArrayList<>();


        if (checkingBadValueUserName(userDto)) { //todo сделать приватный метод на проверку на null и isBlank
            invalidFields.add(USER_NAME);
        }
        if (checkingBadValueEmail(userDto)) { //todo сделать приватный метод на проверку на null и isBlank
            invalidFields.add(EMAIL);
        }
        if (checkingBadValuePassword(userDto)) { //todo сделать приватный метод на проверку на null и isBlank
            invalidFields.add(PASSWORD);
        }
        if (checkingBadValueRoles(userDto)) { //todo сделать приватный метод на проверку на null и isBlank
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
        if (!correctFormatEmail(email)) {
            String message = messageService.getMessage(INCORRECT_EMAIL);
            response.put(EMAIL, message);
            return response;
        }
        if (userDAO.existenceUserEmail(email)) {
            String message = messageService.getMessage(EMAIL_EXIST);
            response.put(EMAIL, message);
            return response;
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

    private boolean correctFormatEmail(String email) {
        return email.trim().matches("(\\w+@\\w+\\.\\w+)");
    }

    private void checkingRole(Map<String, String> response, String role) {
        log.debug("Was calling checkingRole. Input response: {} role: {}", response, role);
        Roles roleUser = Roles.defineRole(role);
        if (roleUser == Roles.UNDEFINED) {
            response.put(ROLES, MessageFormat.format(messageService.getMessage(INCORRECT_ROLE), role));
        } else if (!roleDAO.existenceRoleName(roleUser.name())) {
            response.put(ROLES, MessageFormat.format(messageService.getMessage(ROLE_NOT_FOUND), role));
        }
    }

    private boolean checkingBadValueRoles(UserDto userDto) {
        log.debug("Was calling checkingRoles. Input userDto: {}", userDto);
        return (userDto.getRoles() == null || userDto.getRoles().isEmpty());
    }

    private boolean checkingBadValuePassword(UserDto userDto) {
        log.debug("Was calling checkingPassword. Input userDto: {}", userDto);
        return (userDto.getPassword() == null || userDto.getPassword().isBlank());
    }

    private boolean checkingBadValueEmail(UserDto userDto) {
        log.debug("Was calling checkingEmail. Input userDto: {}", userDto);
        return (userDto.getEmail() == null || userDto.getEmail().isBlank());
    }

    private boolean checkingBadValueUserName(UserDto userDto) {
        log.debug("Was calling checkingBadValueUserName. Input userDto: {}", userDto);
        return (userDto.getUsername() == null || userDto.getUsername().isBlank());
    }
}
