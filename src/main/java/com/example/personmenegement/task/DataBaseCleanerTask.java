package com.example.personmenegement.task;

import com.example.personmenegement.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.jni.Time;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Component
@Service
@RequiredArgsConstructor
public class DataBaseCleanerTask {
    private final PersonRepository personRepository;

    @Scheduled(cron = "${cron.expression}")
    public void deletePersonTask() {
        personRepository.deletePersonOldTask();
    }
}
