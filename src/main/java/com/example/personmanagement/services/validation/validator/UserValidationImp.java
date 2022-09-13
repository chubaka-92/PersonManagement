package com.example.personmanagement.services.validation.validator;

import com.example.personmanagement.api.user.UserChecker;
import com.example.personmanagement.api.user.UserInitializer;
import com.example.personmanagement.api.user.UserValidation;
import com.example.personmanagement.dto.UserDto;
import com.example.personmanagement.services.validation.initializer.UserInitializerImp;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserValidationImp implements UserValidation {

    private final UserChecker userChecker;

    public UserDto validate(UserDto userDto) {
        log.info("Was calling validate. Input userDto: {}", userDto);
        UserInitializer userErrorMessage = new UserInitializerImp(userDto);

        userErrorMessage.addFieldsEmpty(userChecker.checkRequiredFields(userDto));

        if (!userErrorMessage.hasErrors()) {
            userErrorMessage.addIncorrectArgumentMessage(userChecker.checkUserName(userDto.getUsername()));
            userErrorMessage.addIncorrectArgumentMessage(userChecker.checkEmail(userDto.getEmail()));
            userErrorMessage.addIncorrectArgumentMessage(userChecker.checkRoles(userDto.getRoles()));
        }

        if (userErrorMessage.hasErrors()) {
            return userErrorMessage.getUserDtoError();
        }
        return null;
    }

}
