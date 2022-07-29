package com.example.personmenegement.types;

import com.example.personmenegement.services.ResourceBundleServiceImp;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public enum Priority {
    LOW("low"),
    MEDIUM("medium"),
    HIGH("high"),
    CRITICAL("critical"),
    UNDEFINED(null);

    private final String translation;

    public static Priority getDefine(String priority) {
        if (LOW.getTranslation().equals(priority)) {
            return LOW;
        } else if (MEDIUM.getTranslation().equals(priority)) {
            return MEDIUM;
        } else if (HIGH.getTranslation().equals(priority)) {
            return HIGH;
        } else if (CRITICAL.getTranslation().equals(priority)) {
            return CRITICAL;
        } else {
            return UNDEFINED;
        }
    }

    public String getTranslation() {
        return new ResourceBundleServiceImp().getString(translation);
    }
}
