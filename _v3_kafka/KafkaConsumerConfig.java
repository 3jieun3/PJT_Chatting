package com.je.chatting._v3_kafka;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@EnableKafka    // Kafka 활성화
@Configuration
public class KafkaConsumerConfig {

    private final String KAFKA_BROKER;  // (브로커로서의) 카프카 실행 주소

    private final String KAFKA_GROUP;   // consumer 식별할 수 있는 그룹

    public KafkaConsumerConfig(Environment env) {
        this.KAFKA_BROKER = env.getProperty("${spring.kafka.bootstrap-servers}");
        this.KAFKA_GROUP = env.getProperty("${spring.kafka.consumer.group-id}");
    }

    @Bean
    public ConsumerFactory<String, String> kafkaConsumerFactory() {
        Map<String, Object> configMap = new HashMap<>();
        configMap.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, this.KAFKA_BROKER);
        configMap.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        configMap.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        configMap.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        configMap.put("group.id", this.KAFKA_GROUP);
        return new DefaultKafkaConsumerFactory<>(configMap, new StringDeserializer(), new StringDeserializer());
    }
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(kafkaConsumerFactory());
        return factory;
    }

//    @Bean
//    public ConcurrentKafkaListenerContainerFactory<String, KafkaChatMessage> kafkaListenerContainerFactory() {
//        ConcurrentKafkaListenerContainerFactory<String, KafkaChatMessage> factory = new ConcurrentKafkaListenerContainerFactory<>();
//        factory.setConsumerFactory(kafkaConsumerFactory());
//        return factory;
//    }
//
//    @Bean
//    public ConsumerFactory<String, KafkaChatMessage> kafkaConsumerFactory() {
//        return new DefaultKafkaConsumerFactory<>(kafkaConsumerConfiguration(), new StringDeserializer(), new JsonDeserializer<>(KafkaChatMessage.class));
//    }
//
//    @Bean
//    public Map<String, Object> kafkaConsumerConfiguration() {
//        Map<String, Object> configMap = new HashMap<>();
//        // 브로커 주소
//        configMap.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, KAFKA_BROKER);
//        // 받은 데이터 key 값 역직렬화 : key 타입 = String
//        configMap.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
//        // 받은 데이터 value 값 역직렬화 : KafkaChatMessage 타입 = Json
//        configMap.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
//        // topic 의 어떤 offset 부터 받아올 것인지 : consumer 입장에서 가장 오래된 offset 부터
//        configMap.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
//        // consumer 그룹 id
//        configMap.put("group.id", KAFKA_GROUP);
//        return configMap;
//    }

}
