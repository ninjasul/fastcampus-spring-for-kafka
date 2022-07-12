package com.fastcampus.springkafka.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import static com.fastcampus.springkafka.constant.Contants.CLIP6_LISTENER;
import static com.fastcampus.springkafka.constant.Contants.CLIP6_TOPIC;

@Service
@Slf4j
public class Clip6Consumer {

    @KafkaListener(id = CLIP6_LISTENER, topics = CLIP6_TOPIC)
    public void listen(String message) {
        log.info("message: {}", message);
    }
}
