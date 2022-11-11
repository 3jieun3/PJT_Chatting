package com.je.chatting._v3_kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/kafka")
public class KafkaChatController {

    private final KafkaProducer kafkaProducer;

    @MessageMapping("/message")
    public void sendMessage(String message) throws Exception {
        log.info("sending message: {}", message.toString());
        kafkaProducer.send("${spring.kafka.template.default-topic}", message);
    }
}
