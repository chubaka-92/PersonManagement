package com.example.personmenegement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
// todo лучше перенести эту аннотацию в DataBaseCleanerTask
//  done
public class PersonManagementApplication {
    public static void main(String[] args) {
        SpringApplication.run(PersonManagementApplication.class, args);
    }

}
