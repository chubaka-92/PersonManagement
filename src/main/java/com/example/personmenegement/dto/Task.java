package com.example.personmenegement.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Task {

    private String id;
    private String uid;
    private String description;
    private String priority;

    @JsonIgnore
    private boolean valid = true;

    @Override
    public String toString() {
        return "TaskEntity[id: " + id + ", uid: " + uid + ", description: " + description + ", priority: " + priority + "]";
    }
}
