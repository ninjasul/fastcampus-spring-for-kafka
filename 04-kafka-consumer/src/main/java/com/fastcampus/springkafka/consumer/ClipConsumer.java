package com.fastcampus.springkafka.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.listener.adapter.ConsumerRecordMetadata;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.TimeZone;

@Service
@Slf4j
public class ClipConsumer {
    /**
     * concurrency: 생성할 Thread의 개수
     * @param message
     */
    @KafkaListener(
        id = "clip4-listener-id",
        topics = "clip4-listener",
        concurrency = "2",
        clientIdPrefix = "listener_id"
    )
    public void listen(String message,
                       @Header(KafkaHeaders.OFFSET) long offset,
                       @Header(KafkaHeaders.RECEIVED_TIMESTAMP) long timestamp,
                       @Header(KafkaHeaders.RECEIVED_PARTITION_ID) long partition) {
        log.info("Listener, offset: {}, partition: {}, timestamp: {}, message: {}",
                offset,
                partition,
                LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp),
                TimeZone.getDefault().toZoneId()),
                message
        );
    }
}
