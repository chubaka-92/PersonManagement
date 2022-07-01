package com.example.personmenegement.types;

import java.math.BigDecimal;

public enum Position {

    INTERN(BigDecimal.valueOf(15000),BigDecimal.valueOf(20000),"Стажер",0.0),
    TECHNOLOGIST(BigDecimal.valueOf(20000),BigDecimal.valueOf(30000),"Технолог",1.0),
    ENGINEER(BigDecimal.valueOf(30000),BigDecimal.valueOf(40000),"Инженер",2.0),
    LEAD_ENGINEER(BigDecimal.valueOf(40000),BigDecimal.valueOf(55000),"Ведущий инженер", 10.0),
    CHIEF_ENGINEER(BigDecimal.valueOf(55000),BigDecimal.valueOf(65000),"Главный инженер",15.0);

    // todo почему double в Enum, а в сущности Int?
    //  done. определился с типом наконец то)

    private final BigDecimal salaryMin;
    private final BigDecimal salaryMax;
    private final String translation;
    private final Double workExperience;

    Position(BigDecimal salaryMin,BigDecimal salaryMax,String translation,Double workExperience){
    this.salaryMin = salaryMin;
    this.salaryMax = salaryMax;
    this.translation = translation;
    this.workExperience = workExperience; //стаж работы на позиции в годах
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
    public String toString(){
        return translation;
    }
}
