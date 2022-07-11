package com.fastcampus.springkafka;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TopicManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(TopicManagementApplication.class, args);
    }

    @Bean
    public ApplicationRunner runner() {
        return args -> {
        };
    }
}
