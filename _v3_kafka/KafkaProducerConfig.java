package com.je.chatting._v3_kafka;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.*;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@EnableKafka    // Kafka 활성화
@Configuration
public class KafkaProducerConfig {

    private final String KAFKA_BROKER;  // (브로커로서의) 카프카 실행 주소

    private final String KAFKA_GROUP;   // consumer 식별할 수 있는 그룹

    public KafkaProducerConfig(Environment env) {
        this.KAFKA_BROKER = env.getProperty("${spring.kafka.bootstrap-servers}");
        this.KAFKA_GROUP = env.getProperty("${spring.kafka.consumer.group-id}");
    }

//    @Bean
//    public ProducerFactory<String, String> kafkaProducerFactory() {
//        Map<String, Object> configMap = new HashMap<>();
//        configMap.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, this.KAFKA_BROKER);
//        configMap.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
//        configMap.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
//        configMap.put("group.id", this.KAFKA_GROUP);
//        return new DefaultKafkaProducerFactory<>(configMap);
//    }
//
//    @Bean
//    public KafkaTemplate<String, String> kafkaTemplate() {
//        return new KafkaTemplate<>(kafkaProducerFactory());
//    }

    @Bean
    public ProducerFactory<String, KafkaChatMessage> kafkaProducerFactory() {
        return new DefaultKafkaProducerFactory<>(kafkaProducerConfiguration(), new StringSerializer(), new JsonSerializer<>());
    }

    @Bean
    public KafkaTemplate<String, KafkaChatMessage> kafkaTemplate() {
        return new KafkaTemplate<>(kafkaProducerFactory());
    }

    @Bean
    public Map<String, Object> kafkaProducerConfiguration() {
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
