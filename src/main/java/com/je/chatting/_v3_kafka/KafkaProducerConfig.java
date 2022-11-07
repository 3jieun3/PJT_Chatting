package com.je.chatting._v3_kafka;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.*;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@EnableKafka    // Kafka 활성화
@RequiredArgsConstructor
@Configuration
public class KafkaProducerConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private final String KAFKA_BROKER;  // (브로커로서의) 카프카 실행 주소

    @Value("${spring.kafka.consumer.group_id}")
    private final String KAFKA_GROUP;   // consumer 식별할 수 있는 그룹

    @Bean
    public ProducerFactory<String, KafkaChatMessage> kafkaProducerFactory() {
        return new DefaultKafkaProducerFactory<>(kafkaProducerConfig());
    }

    @Bean
    public KafkaTemplate<String, KafkaChatMessage> kafkaTemplate() {
        return new KafkaTemplate<>(kafkaProducerFactory());
    }

    @Bean
    public Map<String, Object> kafkaProducerConfig() {
        Map<String, Object> configMap = new HashMap<>();
        // 브로커 주소
        configMap.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, this.KAFKA_BROKER);
        // kafka 로 보내는 데이터의 key 값 직렬화 : key 타입 = String
        configMap.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        // kafka 로 보내는 데이터의 value 값 직렬화 : KafkaChatMessage 타입 = Json
        configMap.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        // consumer 그룹 id
        configMap.put("group.id", this.KAFKA_GROUP);
        return configMap;
    }

}
