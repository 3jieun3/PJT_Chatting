package com.je.chatting._v3_kafka;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@EnableKafka    // Kafka 활성화
@RequiredArgsConstructor
@Configuration
public class KafkaConsumerConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private final String KAFKA_BROKER;  // (브로커로서의) 카프카 실행 주소

    @Value("${spring.kafka.consumer.group_id}")
    private final String KAFKA_GROUP;   // consumer 식별할 수 있는 그룹

    @Value("${spring.kafka.template.default-topic}")
    private final String KAFKA_TOPIC;   // 토픽

    @Bean
    public Map<String, Object> kafkaConsumerConfig() {
        Map<String, Object> configMap = new HashMap<>();
        // 브로커 주소
        configMap.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, this.KAFKA_BROKER);
        // 데이터의 key 값 역직렬화 : key 타입 = String
        configMap.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        // 데이터의 value 값 역직렬화 : KafkaChatMessage 타입 = Json
        configMap.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        // consumer 그룹 id
        configMap.put("group.id", this.KAFKA_GROUP);
        return configMap;
    }

}
