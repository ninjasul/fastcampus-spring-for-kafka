package com.fastcampus.springkafka.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ClipConsumer {

    @KafkaListener(id = "clip3-id", topics = "03-producer")
    public void listenClip3(String message) {
        log.info("consumed message: {}", message);
    }

    @KafkaListener(id = "clip3-bytesId", topics = "03-producer-bytes")
    public void listenClip3Bytes(String message) {
        log.info("consumed message: {}", message);
    }
}
