package com.fastcampus.springkafka;

import com.fastcampus.springkafka.model.Animal;
import com.fastcampus.springkafka.producer.ClipProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.listener.KafkaMessageListenerContainer;

import static com.fastcampus.springkafka.constant.Constants.CLIP7_LISTENER;
import static com.fastcampus.springkafka.constant.Constants.CLIP7_TOPIC;

@SpringBootApplication
@Slf4j
public class ErrorHandlingApplication {

    public static void main(String[] args) {
        SpringApplication.run(ErrorHandlingApplication.class, args);
    }

    public ApplicationRunner listenerContainerRunner(ClipProducer clipProducer,
                                                     KafkaMessageListenerContainer<String, String> kafkaMessageListenerContainer) {
        return args -> {
            clipProducer.sendAsync("clip4", "Hello, Clip4 Container.");

            kafkaMessageListenerContainer.start();
            Thread.sleep(1_000L);
            log.info("---- started ----");

            kafkaMessageListenerContainer.pause();
            Thread.sleep(5_000L);
            log.info("---- paused ----");

            clipProducer.sendAsync("clip4", "Hello, Secondly Clip4 Container.");

            kafkaMessageListenerContainer.resume();
            Thread.sleep(1_000L);
            log.info("---- resumed ----");

            kafkaMessageListenerContainer.stop();
            log.info("---- stopped ----");
        };
    }

    public ApplicationRunner stringSendAsyncRunner(ClipProducer clipProducer) {
        return args -> {
            clipProducer.sendAsync(CLIP7_LISTENER, "Hello, Clip4 Listener.");
        };
    }

    @Bean
    public ApplicationRunner animalSendAsyncRunner(ClipProducer clipProducer) {
        return args -> {
            clipProducer.sendAsync(CLIP7_TOPIC, new Animal("puppy", 9));
        };
    }
}
