package com.fastcampus.springkafka.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

import static com.fastcampus.springkafka.constant.Contants.CLIP6_TOPIC;

@RestController
@RequiredArgsConstructor
public class Clip6Controller {
    private final KafkaTemplate<String, String> kafkaTemplate;

    @GetMapping("produce")
    public String produce() throws InterruptedException {
        while (true) {
            kafkaTemplate.send(CLIP6_TOPIC, String.valueOf(new Date().getTime()));
            Thread.sleep(100L);
        }
    }
}
