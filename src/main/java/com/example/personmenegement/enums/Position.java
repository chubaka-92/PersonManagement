package com.example.personmenegement.enums;

public enum Position {

    INTERN(15000,20000,"Стажер"),
    TECHNOLOGIST(20000,30000,"Технолог"),
    ENGINEER(30000,40000,"Инженер"),
    LEAD_ENGINEER(40000,55000,"Ведущий инженер"),
    CHIEF_ENGINEER(55000,65000,"Главный инженер");

    // todo почему double в Enum, а в сущности Int?
    private double salaryMin;
    private double salaryMax;
    private String translation;

    Position(double salaryMin,double salaryMax,String translation){
    this.salaryMin = salaryMin;
    this.salaryMax = salaryMax;
    this.translation = translation;
    }

    public double getSalaryMin() {
        return salaryMin;
    }

    public double getSalaryMax() {
        return salaryMax;
    }

    @Override
    public String toString(){
        return translation;
    }
}
