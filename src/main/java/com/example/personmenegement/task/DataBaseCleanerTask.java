package com.example.personmenegement.task;

import com.example.personmenegement.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

// todo Component и Service это одни и те же аннотации. Тут лучше вообще использовать @Configuration
//  done
@Configuration
@RequiredArgsConstructor
@EnableScheduling
public class DataBaseCleanerTask {
    private final PersonRepository personRepository;

    @Scheduled(cron = "${cron.expression}")
    public void deletePersonTask() {
        personRepository.deletePersonOldTask();
    }
}
