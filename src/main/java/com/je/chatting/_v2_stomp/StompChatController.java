package com.je.chatting._v2_stomp;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;

@Slf4j
@RequiredArgsConstructor
@Controller
public class StompChatController {  // publisher

    private final SimpMessageSendingOperations messagingTemplate;

    @MessageMapping("/chat/join")
    public void join(StompChatMessage message) {
        log.info("{} join {}!" + message.getSender(), message.getRoomName());
        message.setContent(message.getSender() + " join this CHAT!");
        messagingTemplate.convertAndSend("/sub/chat/room/" + message.getRoomName(), message);
    }

    @MessageMapping("/chat/message")
    public void message(StompChatMessage message) {
        log.info(message.getSender() + "가 보낸 채팅 : {}", message.getContent());
        messagingTemplate.convertAndSend("/sub/chat/room/" + message.getRoomName(), message);
    }
}