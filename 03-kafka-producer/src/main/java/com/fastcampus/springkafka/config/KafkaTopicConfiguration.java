package com.fastcampus.springkafka.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;

@Configuration
public class KafkaTopicConfiguration {
    @Bean
    public KafkaAdmin.NewTopics clip3Topic() {
        return new KafkaAdmin.NewTopics(
                TopicBuilder.name("03-producer").build(),
                TopicBuilder.name("03-producer-bytes").build(),
                TopicBuilder.name("03-producer-request").build(),
                TopicBuilder.name("03-producer-replies").build()
        );
    }
}
