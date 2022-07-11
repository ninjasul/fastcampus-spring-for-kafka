package com.fastcampus.springkafka.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.*;
import org.apache.kafka.common.KafkaFuture;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.config.ConfigResource;
import org.apache.kafka.common.config.TopicConfig;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.logging.Handler;

import static com.fastcampus.springkafka.constant.Constants.CLIP4_LISTENER;

@Service
@Slf4j
public class KafkaManager {
    private final KafkaAdmin kafkaAdmin;
    private final AdminClient adminClient;

    public KafkaManager(KafkaAdmin kafkaAdmin) {
        this.kafkaAdmin = kafkaAdmin;
        this.adminClient = AdminClient.create(kafkaAdmin.getConfigurationProperties());
    }

    public void describeTopicConfigs() throws ExecutionException, InterruptedException {
        Collection<ConfigResource> resources = List.of(
            new ConfigResource(ConfigResource.Type.TOPIC, CLIP4_LISTENER)
        );

        DescribeConfigsResult result = adminClient.describeConfigs(resources);
        log.info("result: {}", result.all().get());
    }

    public void changeConfig() throws ExecutionException, InterruptedException {
        ConfigResource resource = new ConfigResource(ConfigResource.Type.TOPIC, CLIP4_LISTENER);
        HashMap<ConfigResource, Collection<AlterConfigOp>> ops = new HashMap<>();
        ops.put(resource, List.of(new AlterConfigOp(new ConfigEntry(TopicConfig.RETENTION_MS_CONFIG, "10000"), AlterConfigOp.OpType.SET)));

        adminClient.incrementalAlterConfigs(ops);
    }

    public void deleteConfig() throws ExecutionException, InterruptedException {
        ConfigResource resource = new ConfigResource(ConfigResource.Type.TOPIC, CLIP4_LISTENER);
        HashMap<ConfigResource, Collection<AlterConfigOp>> ops = new HashMap<>();
        ops.put(resource, List.of(new AlterConfigOp(new ConfigEntry(TopicConfig.RETENTION_MS_CONFIG, null), AlterConfigOp.OpType.DELETE)));

        adminClient.incrementalAlterConfigs(ops);
    }

    public void deleteRecords() {
        TopicPartition tp = new TopicPartition(CLIP4_LISTENER, 0);
        Map<TopicPartition, RecordsToDelete> target = new HashMap<>();
        target.put(tp, RecordsToDelete.beforeOffset(1));

        DeleteRecordsResult deleteRecordsResult = adminClient.deleteRecords(target);
        Map<TopicPartition, KafkaFuture<DeletedRecords>> result = deleteRecordsResult.lowWatermarks();

        result.entrySet().forEach(entry -> {
            try {
                log.info("entry - topic: {}, partition: {}, value: {}", entry.getKey().topic(), entry.getKey().partition(), entry.getValue().get().lowWatermark());
            } catch (Exception e) {
                log.error("", e);
            }
        });
    }
}
