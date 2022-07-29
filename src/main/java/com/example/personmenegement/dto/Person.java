package com.example.personmenegement.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Person {

    private String id;
    private String name;
    private String age;
    private String email;
    private String position;
    private String salary;

    @JsonIgnore
    private boolean valid = true;
    private String experience;
    private List<Task> tasks;

}
