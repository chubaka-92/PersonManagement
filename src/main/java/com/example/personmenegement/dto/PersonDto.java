package com.example.personmenegement.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PersonDto {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String id;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String uid;
    private String name;
    private String age;
    private String email;
    private String position;
    private String salary;
    private String experience;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<TaskDto> tasks;
    @JsonIgnore
    private boolean valid = true;
    @JsonIgnore
    private String error;

}
