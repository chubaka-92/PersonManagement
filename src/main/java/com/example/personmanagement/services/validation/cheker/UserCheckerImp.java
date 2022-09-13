package com.example.personmanagement.services.validation.cheker;

import com.example.personmanagement.api.role.RoleDAO;
import com.example.personmanagement.api.user.UserChecker;
import com.example.personmanagement.api.user.UserDAO;
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
    private final MessageService messageService = new MessageService();
    private final UserDAO userDAO;
    private final RoleDAO roleDAO;

    @Override
    public List<String> checkRequiredFields(UserDto userDto) {
        log.info("Was calling checkRequiredFields. Input userDto: {}", userDto);
        List<String> invalidFields = new ArrayList<>();
        checkingUserName(userDto, invalidFields);
        checkingEmail(userDto, invalidFields);
        checkingPassword(userDto, invalidFields);
        checkingRoles(userDto, invalidFields);

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
        log.debug("Was calling checkingRole. Input response: {} role: {}", response, role); //todo почему debug ?  // DONE. излишняя информация для инфо
        Roles roleUser = Roles.defineRole(role);
        if (roleUser == Roles.UNDEFINED) {
            //todo поместить в put
            //  DONE
            response.put(ROLES, MessageFormat.format(messageService.getMessage(INCORRECT_ROLE), role));
        } else if (!roleDAO.existenceRoleName(roleUser.name())) {
            //todo поместить в put
            //  DONE
            response.put(ROLES, MessageFormat.format(messageService.getMessage(ROLE_NOT_FOUND), role));
        }
    }

    private void checkingRoles(UserDto userDto, List<String> invalidFields) {
        log.debug("Was calling checkingRoles. Input userDto: {} invalidFields: {}", userDto, invalidFields);
        if (userDto.getRoles() == null || userDto.getRoles().isEmpty()) { //todo сделать приватный метод на проверку на null и isBlank     //  DONE
            invalidFields.add(ROLES);
        }
    }

    private void checkingPassword(UserDto userDto, List<String> invalidFields) {
        log.debug("Was calling checkingPassword. Input userDto: {} invalidFields: {}", userDto, invalidFields);
        if (userDto.getPassword() == null || userDto.getPassword().isBlank()) { //todo сделать приватный метод на проверку на null и isBlank   //  DONE
            invalidFields.add(PASSWORD);
        }
    }

    private void checkingEmail(UserDto userDto, List<String> invalidFields) {
        log.debug("Was calling checkingEmail. Input userDto: {} invalidFields: {}", userDto, invalidFields);
        if (userDto.getEmail() == null || userDto.getEmail().isBlank()) { //todo сделать приватный метод на проверку на null и isBlank   //  DONE
            invalidFields.add(EMAIL);

        }
    }

    private void checkingUserName(UserDto userDto, List<String> invalidFields) {
        log.debug("Was calling checkingUserName. Input userDto: {} invalidFields: {}", userDto, invalidFields);
        if (userDto.getUsername() == null || userDto.getUsername().isBlank()) { //todo сделать приватный метод на проверку на null и isBlank   //  DONE
            invalidFields.add(USER_NAME);
        }
    }
}
