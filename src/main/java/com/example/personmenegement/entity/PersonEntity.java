package com.example.personmenegement.entity;

import javax.persistence.*;

@Entity
@Table(name="person")
public class PersonEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private long id; // todo обычно используется Long-обертка. Таким образом можно понимать задано ли значение id или нет

    private String name;

    private int age; // todo обертка

    private String email;
    private String position;//енам надо прикрутить // todo можешь сделать поле enum и повесить аннотацию @Enumerated

    private long salary; // todo обертка

    // todo используй lombok
    public PersonEntity(String name, int age, String email, String position, long salary) {
        setName(name);
        setAge(age);
        setEmail(email);
        setPosition(position);
        setSalary(salary);
    }

    public PersonEntity() {

    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }

    public String getPosition() {
        return position;
    }

    public double getSalary() {
        return salary;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setSalary(long salary) {
        this.salary = salary;
    }
}
