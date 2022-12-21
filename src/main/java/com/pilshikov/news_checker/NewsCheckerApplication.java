package com.pilshikov.news_checker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class NewsCheckerApplication {

    public static void main(String[] args) {
        SpringApplication.run(NewsCheckerApplication.class, args);
    }

}
