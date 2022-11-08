package com.je.chatting._v3_kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class kafkaProducer {

    private final KafkaTemplate<String, KafkaChatMessage> kafkaTemplate;

    public void send(String topic, KafkaChatMessage message) {
        log.info("sending message: {} to topic: {}", message.toString(), topic);
        kafkaTemplate.send(topic, message);     // send to clients via websocket(STOMP)
    }

}