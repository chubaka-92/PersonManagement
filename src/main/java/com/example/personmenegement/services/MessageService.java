package com.example.personmenegement.services;

import lombok.AllArgsConstructor;

import java.util.ResourceBundle;

@AllArgsConstructor
public class MessageService {// todo тут интерфейс лишний  //   DONE

    private static final String MESSAGE = "message";
    private final ResourceBundle resourceBundle = ResourceBundle.getBundle(MESSAGE);

    public String getMessage(String constant) {
        return resourceBundle.getString(constant);
    }

    public String getMessage(Enum a) {
        return getMessage(a.name());
    }
}
