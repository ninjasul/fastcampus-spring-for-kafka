package com.fastcampus.springkafka;

import org.apache.kafka.common.Metric;
import org.apache.kafka.common.MetricName;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.MessageListenerContainer;

import java.util.Map;

import static com.fastcampus.springkafka.constant.Contants.CLIP6_LISTENER;

@SpringBootApplication
public class KafkaMonitoringApplication {

    public static void main(String[] args) {
        SpringApplication.run(KafkaMonitoringApplication.class, args);
    }

    @Bean
    ApplicationRunner runner(KafkaTemplate<String, String> kafkaTemplate, KafkaListenerEndpointRegistry kafkaListenerEndpointRegistry) {
        return args -> {
            Map<MetricName, ? extends Metric> producerMetrics = kafkaTemplate.metrics();

            MessageListenerContainer container = kafkaListenerEndpointRegistry.getListenerContainer(CLIP6_LISTENER);
            Map<String, Map<MetricName, ? extends Metric>> consumerMetrics = container.metrics();
        };
    }
}
