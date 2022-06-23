package com.example.personmenegement.enums;

import java.math.BigDecimal;

public enum Position {

    INTERN(BigDecimal.valueOf(15000),BigDecimal.valueOf(20000),"Стажер"),
    TECHNOLOGIST(BigDecimal.valueOf(20000),BigDecimal.valueOf(30000),"Технолог"),
    ENGINEER(BigDecimal.valueOf(30000),BigDecimal.valueOf(40000),"Инженер"),
    LEAD_ENGINEER(BigDecimal.valueOf(40000),BigDecimal.valueOf(55000),"Ведущий инженер"),
    CHIEF_ENGINEER(BigDecimal.valueOf(55000),BigDecimal.valueOf(65000),"Главный инженер");

    // todo почему double в Enum, а в сущности Int?
    // done. определился с типом наконец то)
    private final BigDecimal salaryMin;
    private final BigDecimal salaryMax;
    private final String translation;

    Position(BigDecimal salaryMin,BigDecimal salaryMax,String translation){
    this.salaryMin = salaryMin;
    this.salaryMax = salaryMax;
    this.translation = translation;
    }

    @Override
    public String toString(){
        return translation;
    }
}
