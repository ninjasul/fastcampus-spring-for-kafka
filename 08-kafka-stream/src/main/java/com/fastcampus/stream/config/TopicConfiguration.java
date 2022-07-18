package com.fastcampus.stream.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

import static com.fastcampus.stream.constant.Constants.CLIP8_TOPIC;

@Configuration
public class TopicConfiguration {

    @Bean
    public NewTopic topic() {
        return TopicBuilder.name(CLIP8_TOPIC).build();
    }
}
