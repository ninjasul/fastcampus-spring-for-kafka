package com.fastcampus.stream.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import static com.fastcampus.stream.constant.Constants.*;

@Service
@Slf4j
public class Clip8Consumer {

    @KafkaListener(id = CLIP8_TO_LISTENER_ID, topics = CLIP8_TO_TOPIC)
    public void listen(String message) {
        log.info("Listener. message: {}", message);
    }
}
