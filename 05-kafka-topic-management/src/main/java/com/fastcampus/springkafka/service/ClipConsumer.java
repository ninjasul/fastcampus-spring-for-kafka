package com.fastcampus.springkafka.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.listener.AbstractConsumerSeekAware;
import org.springframework.stereotype.Service;

import static com.fastcampus.springkafka.constant.Constants.CLIP5_LISTENER;
import static com.fastcampus.springkafka.constant.Constants.CLIP5_LISTENER_ID;

@Service
@Slf4j
public class ClipConsumer extends AbstractConsumerSeekAware {

    @KafkaListener(id = CLIP5_LISTENER_ID, topics = CLIP5_LISTENER)
    public void listen(String message) {
        log.info("message: {}", message);
    }

    public void seek() {
        getSeekCallbacks().forEach(((tp, consumerSeekCallback) -> consumerSeekCallback.seek(tp.topic(), tp.partition(), 0)));
    }
}
