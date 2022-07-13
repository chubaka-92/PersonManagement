package com.example.personmenegement.types;

import java.math.BigDecimal;

public enum Position {

    INTERN(BigDecimal.valueOf(15000), BigDecimal.valueOf(20000), "Стажер", 0.0),
    TECHNOLOGIST(BigDecimal.valueOf(20000), BigDecimal.valueOf(30000), "Технолог", 1.0),
    ENGINEER(BigDecimal.valueOf(30000), BigDecimal.valueOf(40000), "Инженер", 2.0),
    LEAD_ENGINEER(BigDecimal.valueOf(40000), BigDecimal.valueOf(55000), "Ведущий инженер", 10.0),
    CHIEF_ENGINEER(BigDecimal.valueOf(55000), BigDecimal.valueOf(65000), "Главный инженер", 15.0),
    UNDEFINED(null, null, null, null); // todo можно просто эти поля сделать null + поменяй название //  DONE

    private final BigDecimal salaryMin;
    private final BigDecimal salaryMax;
    private final String translation;
    private final Double workExperience;

    Position(BigDecimal salaryMin, BigDecimal salaryMax, String translation, Double workExperience) {
        this.salaryMin = salaryMin;
        this.salaryMax = salaryMax;
        this.translation = translation;
        this.workExperience = workExperience; // todo лишние комментарии
    }                                        //    done

    // todo реформат кода Ctrl + Alt + L
    //  done
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

    public BigDecimal getSalaryMin() {
        return salaryMin;
    }

    public BigDecimal getSalaryMax() {
        return salaryMax;
    }

    public Double getWorkExperience() {
        return workExperience;
    }

    @Override
    public String toString() {
        return translation;
    }
}
