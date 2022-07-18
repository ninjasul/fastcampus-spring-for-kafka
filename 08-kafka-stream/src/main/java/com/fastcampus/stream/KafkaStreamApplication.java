package com.fastcampus.stream;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.Date;

import static com.fastcampus.stream.constant.Constants.CLIP8_TOPIC;

@SpringBootApplication
public class KafkaStreamApplication {

    public static void main(String[] args) {
        SpringApplication.run(KafkaStreamApplication.class, args);
    }

    @Bean
    public ApplicationRunner runner(KafkaTemplate<String, String> kafkaTemplate) {
        return args -> {
            while (true) {
                kafkaTemplate.send(CLIP8_TOPIC, String.valueOf(new Date().getTime()));
                Thread.sleep(1_000L);
            }
        };
    }

}
