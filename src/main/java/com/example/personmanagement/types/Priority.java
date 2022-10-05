package com.example.personmanagement.types;

import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ToString
public enum Priority {
    LOW,
    MEDIUM,
    HIGH,
    CRITICAL,
    UNDEFINED;
}
