package com.fastcampus.springkafka;

import com.fastcampus.springkafka.service.ClipConsumer;
import com.fastcampus.springkafka.service.KafkaManager;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;

import static com.fastcampus.springkafka.constant.Constants.CLIP5_LISTENER;

@SpringBootApplication
public class TopicManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(TopicManagementApplication.class, args);
    }

    @Bean
    public ApplicationRunner runner(
            KafkaManager kafkaManager,
            KafkaTemplate<String, String> kafkaTemplate,
            ClipConsumer consumer
    ) {
        return args -> {
/*
            kafkaManager.describeTopicConfigs();
            kafkaManager.changeConfig();
            kafkaManager.describeTopicConfigs();
            kafkaManager.deleteConfig();
            kafkaManager.describeTopicConfigs();
            kafkaManager.deleteRecords();
*/

            kafkaManager.findAllConsumerGroup();
            //kafkaManager.deleteConsumerGroup();
            //kafkaManager.findAllConsumerGroup();
            kafkaManager.findAllOffsets();

            kafkaTemplate.send(CLIP5_LISTENER, "Hello, Listener.");
            consumer.seek();
        };
    }
}
