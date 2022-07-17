package com.fastcampus.springkafka.consumer;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import static com.fastcampus.springkafka.constant.Contants.*;

@Service
@Slf4j
public class Clip6Consumer {
    private final Counter counter;

    public Clip6Consumer(MeterRegistry meterRegistry) {
        this.counter = meterRegistry.counter(CLIP6_LISTENER_COUNTER, CLIP6_LISTENER_COUNTER_TAG_NAME, CLIP6_LISTENER);
    }

    @KafkaListener(id = CLIP6_LISTENER, topics = CLIP6_TOPIC)
    public void listen(String message) {
        log.info("message: {}", message);
        counter.increment();
    }
}
