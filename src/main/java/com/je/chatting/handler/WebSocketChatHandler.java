package com.je.chatting.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.je.chatting.domain.ChatMessage;
import com.je.chatting.domain.ChatRoom;
import com.je.chatting.service.ChatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Component
public class WebSocketChatHandler extends TextWebSocketHandler {

    private final ObjectMapper objectMapper;

    private final ChatService chatService;

    /* client 가 채팅 메시지 전송 요청 시 (데이터 통신 중) */
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String payload = message.getPayload();
        log.info("payload : {}", payload);

        ChatMessage chatMessage = objectMapper.readValue(payload, ChatMessage.class);
        ChatRoom chatRoom = chatService.findRoomById(chatMessage.getRoomId());
        chatRoom.handleActions(session, chatMessage, chatService);
    }

//    /* 채팅방 접속 시 */
//    @Override
//    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
//        log.info(session + " client join JE CHAT!");
//        sessionList.add(session);
//
//        TextMessage textMessage = new TextMessage("WELCOME TO JE CHAT!");
//        session.sendMessage(textMessage);
//
//        for (WebSocketSession sess : sessionList) {     // 접속한 세션제외하고 채팅방의 모든 다른 세션들에게 전달
//            if (sess != session)
//                sess.sendMessage(new TextMessage(session.getId() + " JOIN THIS JE CHAT!"));
//        }
//    }
//
//    /* 채팅방 접속 해제 시 */
//    @Override
//    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
//        log.info(session + " client leave JE CHAT!");
//        sessionList.remove(session);
//
//        for (WebSocketSession sess : sessionList) {     // 채팅방의 모든 다른 세션들에게 전달
//            sess.sendMessage(new TextMessage(session.getId() + " LEAVE THIS JE CHAT!"));
//        }
//    }
}
