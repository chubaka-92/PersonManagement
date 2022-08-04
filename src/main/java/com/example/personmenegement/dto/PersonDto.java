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
public class PersonDto {

    private String id;
    private String name;
    private String age;
    private String email;
    private String position;
    private String salary;
    private String experience;
    private List<TaskDto> tasksDto;
    @JsonIgnore
    private boolean valid = true;
    @JsonIgnore
    private String error;

}
