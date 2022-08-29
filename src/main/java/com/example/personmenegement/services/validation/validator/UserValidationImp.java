package com.example.personmenegement.services.validation.validator;

import com.example.personmenegement.api.UserChecker;
import com.example.personmenegement.api.UserInitializer;
import com.example.personmenegement.api.UserValidation;
import com.example.personmenegement.dto.UserDto;
import com.example.personmenegement.services.validation.initializer.UserInitializerImp;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserValidationImp implements UserValidation {

    private final UserChecker userChecker;

    public UserDto validate(UserDto userDto) {
        log.info("Was calling validate. Input userDto: " + userDto);
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
