package com.example.personmenegement.api;

import com.example.personmenegement.dto.Task;

import java.util.List;
import java.util.Map;

public interface TaskChecker {

    List<String> checkRequiredFields(Task task);


    Map<String, String> checkPriority(String priority);
}
