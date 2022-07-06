package com.fastcampus.springkafka.producer;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaProducerException;
import org.springframework.kafka.core.KafkaSendCallback;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

@Service
@RequiredArgsConstructor
public class ClipProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;

    public void sendAsync(String topic, String message) {
        ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(topic, message);
        future.addCallback(new KafkaTemplateSendAsyncCallback());
    }

}
