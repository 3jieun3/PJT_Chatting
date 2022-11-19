package com.je.chatting._v3_kafka.controller;

import com.je.chatting._v3_kafka.domain.KafkaChatMessage;
import com.je.chatting._v3_kafka.service.KafkaProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

@Slf4j
@RequiredArgsConstructor
@Controller
public class KafkaChatController {

    @Value("${spring.kafka.template.default-topic}")
    private String KAFKA_TOPIC;

    private final KafkaProducer kafkaProducer;

    @MessageMapping("/message")
    public void sendMessage(KafkaChatMessage message) {
        kafkaProducer.send(this.KAFKA_TOPIC, message);
    }
}
