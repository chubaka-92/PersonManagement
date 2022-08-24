package com.example.personmenegement.services;

import lombok.extern.slf4j.Slf4j;

import java.util.ResourceBundle;

@Slf4j
public class MessageService {// todo тут интерфейс лишний  //   DONE

    private static final String MESSAGE = "message";
    private final ResourceBundle resourceBundle = ResourceBundle.getBundle(MESSAGE);

    public String getMessage(String constant) {
        log.debug("Was calling getMessage. Input constant: {}", constant);
        return resourceBundle.getString(constant);
    }

    public String getMessage(Enum constant) {
        log.debug("Was calling getMessage. Input constant: {}", constant);
        return getMessage(constant.name());
    }
}
