package com.je.chatting._v3_kafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@EnableKafka
@Configuration
public class KafkaProducerConfig {

    private final String KAFKA_BROKER;  // (브로커로서의) 카프카 실행 주소

    private final String KAFKA_GROUP;   // consumer 식별할 수 있는 그룹

    public KafkaProducerConfig(
            @Value("${spring.kafka.producer.bootstrap-servers}") String kafka_broker,
            @Value("${spring.kafka.consumer.group-id}") String kafka_group) {
        this.KAFKA_BROKER = kafka_broker;
        this.KAFKA_GROUP = kafka_group;
        log.info("broker = {} & group = {}", this.KAFKA_BROKER, this.KAFKA_GROUP);
    }

    @Bean
    public ProducerFactory<String, KafkaChatMessage> kafkaProducerFactory() {
        return new DefaultKafkaProducerFactory<>(kafkaProducerConfiguration(),
                new StringSerializer(),
                new JsonSerializer<KafkaChatMessage>());
    }

    @Bean
    public KafkaTemplate<String, KafkaChatMessage> kafkaTemplate() {
        return new KafkaTemplate<>(kafkaProducerFactory());
    }

    @Bean
    public Map<String, Object> kafkaProducerConfiguration() {
        Map<String, Object> configMap = new HashMap<>();
        configMap.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, this.KAFKA_BROKER);
        configMap.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configMap.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return configMap;
    }
}
