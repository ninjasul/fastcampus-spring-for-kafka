package com.fastcampus.springkafka.producer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.core.KafkaProducerException;
import org.springframework.kafka.core.KafkaSendCallback;

@Slf4j
public class KafkaTemplateSendAsyncCallback implements KafkaSendCallback {
    @Override
    public void onFailure(KafkaProducerException ex) {
        ProducerRecord<Object, Object> failedRecord = ex.getFailedProducerRecord();
        log.error("Failed to send message. record: {}", failedRecord);
    }

    @Override
    public void onSuccess(Object result) {
        log.info("Succeeded to send message.");
    }
}
