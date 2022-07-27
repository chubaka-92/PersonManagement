package com.example.personmenegement.types;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FieldName {
    NAME("name"),
    AGE("age"),
    POSITION("position"),
    SALARY("salary"),
    EXPERIENCE("experience");

    private final String fieldName;

}
