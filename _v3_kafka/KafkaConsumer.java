package com.je.chatting._v3_kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class KafkaConsumer {

    private final SimpMessageSendingOperations messagingTemplate;

    // kafka 로부터 메시지 받기 위한 어노테이션
    @KafkaListener(
            topics = "${spring.kafka.template.default-topic}",
            groupId = "${spring.kafka.consumer.group-id}"
    )
    public void receive(KafkaChatMessage message) {
        log.info("sending message: {} via kafka listener", message);
        messagingTemplate.convertAndSend("/topic/group", message);
    }

}
