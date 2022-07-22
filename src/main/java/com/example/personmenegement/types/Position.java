package com.example.personmenegement.types;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
@ToString
public enum Position {

    INTERN(BigDecimal.valueOf(15000), BigDecimal.valueOf(20000), "Стажер", 0.0),
    TECHNOLOGIST(BigDecimal.valueOf(20000), BigDecimal.valueOf(30000), "Технолог", 1.0),
    ENGINEER(BigDecimal.valueOf(30000), BigDecimal.valueOf(40000), "Инженер", 2.0),
    LEAD_ENGINEER(BigDecimal.valueOf(40000), BigDecimal.valueOf(55000), "Ведущий инженер", 10.0),
    CHIEF_ENGINEER(BigDecimal.valueOf(55000), BigDecimal.valueOf(65000), "Главный инженер", 15.0),
    UNDEFINED(null, null, null, null);
    private final BigDecimal salaryMin;
    private final BigDecimal salaryMax;
    private final String translation;
    private final Double workExperience;


    public static Position getDefine(String position) {
        if (INTERN.translation.equals(position)) {
            return INTERN;
        } else if (TECHNOLOGIST.translation.equals(position)) {
            return TECHNOLOGIST;
        } else if (ENGINEER.translation.equals(position)) {
            return ENGINEER;
        } else if (LEAD_ENGINEER.translation.equals(position)) {
            return LEAD_ENGINEER;
        } else if (CHIEF_ENGINEER.translation.equals(position)) {
            return CHIEF_ENGINEER;
        } else {
            return UNDEFINED;
        }
    }
    //todo почему не аннотация lombok ?
    //  Done


    public static boolean checkExperienceMatchingPosition(Position positionPerson, String experience) {
        if (positionPerson.getWorkExperience().compareTo(Double.valueOf(experience)) > 0) {
            return false;
        }
        return true;
    }

    public static boolean checkSalaryMatchingPosition(Position positionPerson, BigDecimal salaryPerson) {
        if (positionPerson.getSalaryMin().compareTo(salaryPerson) > 0
                || positionPerson.getSalaryMax().compareTo(salaryPerson) < 0) {
            return false;
        }
        return true;
    }
}
