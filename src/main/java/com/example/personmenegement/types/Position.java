package com.example.personmenegement.types;

import com.example.personmenegement.dto.Task;
import com.example.personmenegement.entity.PersonEntity;
import com.example.personmenegement.services.ResourceBundleServiceImp;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.List;
@Slf4j
@Getter
@ToString
@AllArgsConstructor
public enum Position {

    INTERN(BigDecimal.valueOf(15000), BigDecimal.valueOf(20000), "intern", 0.0,1),
    TECHNOLOGIST(BigDecimal.valueOf(20000), BigDecimal.valueOf(30000), "technologist", 1.0,3),
    ENGINEER(BigDecimal.valueOf(30000), BigDecimal.valueOf(40000), "engineer", 2.0,6),
    LEAD_ENGINEER(BigDecimal.valueOf(40000), BigDecimal.valueOf(55000), "lead_engineer", 10.0,8),
    CHIEF_ENGINEER(BigDecimal.valueOf(55000), BigDecimal.valueOf(65000), "chief_engineer", 15.0,10),
    UNDEFINED(null, null, null, null,null);

    private final BigDecimal salaryMin;
    private final BigDecimal salaryMax;
    private final String translation;
    private final Double workExperience;
    private final Integer countTasks;

    public static Position getDefine(String position) {
        if (INTERN.getTranslation().equals(position)) {
            return INTERN;
        } else if (TECHNOLOGIST.getTranslation().equals(position)) {
            return TECHNOLOGIST;
        } else if (ENGINEER.getTranslation().equals(position)) {
            return ENGINEER;
        } else if (LEAD_ENGINEER.getTranslation().equals(position)) {
            return LEAD_ENGINEER;
        } else if (CHIEF_ENGINEER.getTranslation().equals(position)) {
            return CHIEF_ENGINEER;
        } else {
            return UNDEFINED;
        }
    }

    public String getTranslation() {
        return new ResourceBundleServiceImp().getString(translation);
    }

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

    public static boolean checkAvailableCountTasksToPerson(int countTasks, PersonEntity personEntity) {
        log.info("Was calling checkAvailableCountTasksToPerson. Input personEntity: " + personEntity.toString()
                + " countTasks: " + countTasks);
        if (personEntity.getTasks().size() < personEntity.getPosition().getCountTasks()
                && personEntity.getCountAvailableTasks() >= countTasks) {
            return false;
        }
        return true;
    }

}
