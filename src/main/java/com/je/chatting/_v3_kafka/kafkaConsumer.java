package com.je.chatting._v3_kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class kafkaConsumer {

    private final SimpMessageSendingOperations messagingTemplate;

    // kafka 로부터 메시지 받기 위한 어노테이션
    @KafkaListener
    public void send(String topic, KafkaChatMessage message) {
        log.info("sending message: {} to topic: {}", message.toString(), topic);
        messagingTemplate.send(topic, message);     // send to clients via websocket(STOMP)
    }

}
