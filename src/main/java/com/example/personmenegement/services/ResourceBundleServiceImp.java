package com.example.personmenegement.services;

import com.example.personmenegement.api.ResourceBundleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ResourceBundle;

@Service
@RequiredArgsConstructor
public class ResourceBundleServiceImp implements ResourceBundleService {

    private static final String MESSAGE = "message";
    private final ResourceBundle errorMsg = ResourceBundle.getBundle(MESSAGE);

    public String getString(String constant) {
        return errorMsg.getString(constant);
    }
}
