package com.fastcampus.springkafka;

import com.fastcampus.springkafka.model.Animal;
import com.fastcampus.springkafka.producer.ClipProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import static com.fastcampus.springkafka.constant.Constants.CLIP7_ANIMAL_TOPIC;

@SpringBootApplication
@Slf4j
public class ErrorHandlingApplication {

    public static void main(String[] args) {
        SpringApplication.run(ErrorHandlingApplication.class, args);
    }

    @Bean
    public ApplicationRunner animalSendAsyncRunner(ClipProducer clipProducer) {
        return args -> {
            clipProducer.sendAsync(CLIP7_ANIMAL_TOPIC, new Animal("puppy", 15));
        };
    }
}
