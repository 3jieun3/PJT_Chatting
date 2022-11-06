package com.je.chatting.domain;

import com.je.chatting.service.ChatService;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.socket.WebSocketSession;

import java.util.*;

@Slf4j
@Getter
public class ChatRoom {

    private final String roomId;  // 채팅방 번호

    private final String name;    // 채팅방 이름

    public ChatRoom(String name) {
        this.roomId = UUID.randomUUID().toString();
        this.name = name;
    }

//    private final Set<WebSocketSession> sessionList = new HashSet<>();  // 채팅방에 접속 중인 세션 리스트

//    public void handleActions(WebSocketSession session, ChatMessage chatMessage, ChatService chatService) {
//        if (chatMessage.getType().equals(ChatMessage.MessageType.JOIN)) {           /* 채팅방 접속 시 */
//            log.info("{} join \"{}\"!", session.getId(), name);
//            sessionList.add(session);
//            chatMessage.setContent(String.format("%s JOIN '%s'!", chatMessage.getSender(), name));
//        } else if (chatMessage.getType().equals(ChatMessage.MessageType.LEAVE)) {   /* 채팅방 접속 해제 시 */
//            log.info("{} leave \"{}\"!", session.getId(), name);
//            sessionList.remove(session);
//            chatMessage.setContent(String.format("%s LEAVE '%s'!", chatMessage.getSender(), name));
//        }
//        // 채팅방의 모든 세션에 메시지 전송
//        sessionList.parallelStream().forEach(sess -> chatService.sendMessage(sess, chatMessage));
//    }
}