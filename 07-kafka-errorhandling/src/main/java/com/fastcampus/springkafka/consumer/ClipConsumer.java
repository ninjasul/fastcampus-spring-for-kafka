package com.fastcampus.springkafka.consumer;

import com.fastcampus.springkafka.model.Animal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import javax.validation.Valid;

import static com.fastcampus.springkafka.constant.Constants.CLIP7_ANIMAL_LISTENER_ID;
import static com.fastcampus.springkafka.constant.Constants.CLIP7_ANIMAL_TOPIC;

@Service
@Slf4j
public class ClipConsumer {

    @KafkaListener(
            id = CLIP7_ANIMAL_LISTENER_ID,
            topics = CLIP7_ANIMAL_TOPIC,
            containerFactory = "kafkaJsonContainerFactory"
    )
    public void listenAnimal(@Valid Animal animal) {
        log.info("Animal. animal: {}", animal);
    }
}
