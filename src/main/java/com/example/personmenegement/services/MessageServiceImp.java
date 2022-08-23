package com.example.personmenegement.services;

import com.example.personmenegement.api.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ResourceBundle;

@Service
@RequiredArgsConstructor
public class MessageServiceImp implements MessageService {// todo тут интерфейс лишний

    private static final String MESSAGE = "message";
    private final ResourceBundle resourceBundle = ResourceBundle.getBundle(MESSAGE);

    public String getMessage(String constant) {
        return resourceBundle.getString(constant);
    }

    public String getMessage(Enum a) {
        return getMessage(a.name());
    }
}
