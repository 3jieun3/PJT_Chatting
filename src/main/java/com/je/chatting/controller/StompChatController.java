package com.je.chatting.controller;

import com.je.chatting.domain.ChatMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;

@Slf4j
@RequiredArgsConstructor
@Controller
public class StompChatController {

    private final SimpMessageSendingOperations messagingTemplate;


    @MessageMapping("/chat/join")       // /pub/chat/join
    public void join(ChatMessage message) {
        log.info("{} join {}!" + message.getSender(), message.getRoomId());
        message.setContent(message.getSender() + " join this CHAT!");
        messagingTemplate.convertAndSend("/sub/chat/room/" + message.getRoomId(), message);
    }


    @MessageMapping("/chat/message")    // /pub/chat/message
    public void message(ChatMessage message) {
        log.info(message.getSender() + "가 보낸 채팅 : {}", message.getContent());
        messagingTemplate.convertAndSend("/sub/chat/room/" + message.getRoomId(), message);
    }
}