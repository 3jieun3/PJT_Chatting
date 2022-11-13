package com.je.chatting._v3_kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class KafkaProducer {

    // yml 에 설정해놓은 kafka server 로 바로 통신할 수 있게 하고 이를 통해 메시지 전달
    private final KafkaTemplate<String, KafkaChatMessage> kafkaTemplate;

    public void send(@Value("${spring.kafka.template.default-topic}") String kafka_topic, KafkaChatMessage message) {
        log.info("sending message: {} to topic: {}", message.toString(), kafka_topic);
        this.kafkaTemplate.send(kafka_topic, message);     // send to clients via 
    }
}
