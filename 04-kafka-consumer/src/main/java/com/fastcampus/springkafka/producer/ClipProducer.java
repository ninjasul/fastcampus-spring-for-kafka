package com.fastcampus.springkafka.producer;

import com.fastcampus.springkafka.model.Animal;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

@Service
@RequiredArgsConstructor
@Slf4j
public class ClipProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final KafkaTemplate<String, Animal> kafkaJsonTemplate;

    public void sendAsync(String topic, String message) {
        ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(topic, message);
        future.addCallback(new KafkaTemplateSendAsyncCallback());
    }

    public void sendAsync(String topic, Animal animal) {
        kafkaJsonTemplate.send(topic, animal);
    }
}
