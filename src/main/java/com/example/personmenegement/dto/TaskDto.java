package com.example.personmenegement.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class TaskDto {

    private String id;
    private String uid;
    private String description;
    private String priority;

    @JsonIgnore
    private boolean valid = true;
    // todo используй lombok
    //  DONE
}
