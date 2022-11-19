package com.je.chatting._v3_kafka.service;

import com.je.chatting._v3_kafka.domain.KafkaChatMessage;
import com.je.chatting._v3_kafka.domain.KafkaChatRoom;
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

    private final KafkaChatRoomService kafkaChatRoomService;

    // kafka 로부터 메시지 받기 위한 어노테이션
    @KafkaListener(
            topics = "${spring.kafka.template.default-topic}",
            groupId = "${spring.kafka.consumer.group-id}"
    )
    public void receive(KafkaChatMessage message) {
        log.info("received message: {}", message.toString());
        // roomId 로 해당 채팅방 조회
        KafkaChatRoom kafkaChatRoom = kafkaChatRoomService.findKafkaRoomById(message.getRoomId());
        messagingTemplate.convertAndSend("/topic/room/" + kafkaChatRoom.getName(), message);
    }

}
