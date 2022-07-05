package com.example.personmenegement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling// todo лучше перенести эту аннотацию в DataBaseCleanerTask
public class PersonManagementApplication {
    public static void main(String[] args) {
        SpringApplication.run(PersonManagementApplication.class, args);
    }

}
