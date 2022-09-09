package com.example.personmanagement.config.schedule;

import com.example.personmanagement.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Slf4j
@Configuration
@EnableScheduling
@RequiredArgsConstructor
public class DataBaseCleanerTaskConfig {
    private final PersonRepository personRepository;

    @Scheduled(cron = "${cron.expression}")
    public void deletePersonTask() {
        log.info("Was calling deletePersonTask.");
        personRepository.deletePersonOldTask();
    }
}



