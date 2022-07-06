package com.fastcampus.springkafka;

import com.fastcampus.springkafka.producer.ClipProducer;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    //@Bean
    public ApplicationRunner runner(KafkaTemplate<String, String> kafkaTemplate) {
        return args -> {
            kafkaTemplate.send("03-producer", "Hello, 03-producer");
        };
    }

    @Bean
    public ApplicationRunner producerRunner(ClipProducer clipProducer) {
        return args -> {
            clipProducer.sendAsync("03-producer", "Hello, Clip3-async");
            Thread.sleep(1000L);
        };
    }
}
