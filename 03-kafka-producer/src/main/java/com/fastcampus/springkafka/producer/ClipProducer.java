package com.fastcampus.springkafka.producer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
@Slf4j
public class ClipProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;

    public void sendAsync(String topic, String message) {
        ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(topic, message);
        future.addCallback(new KafkaTemplateSendAsyncCallback());
    }

    public void sendSync(String topic, String message) {
        ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(topic, message);
        try {
            future.get(10, TimeUnit.SECONDS);
            log.info("Succeeded to send a message synchronously.");
        } catch (Throwable t) {
            log.error("Failed to execute sendSync()", t);
        }
    }

}
