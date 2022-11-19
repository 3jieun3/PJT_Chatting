package com.je.chatting._v1_webSocket.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.je.chatting._v1_webSocket.sevice.ChatService;
import com.je.chatting._v1_webSocket.domain.ChatMessage;
import com.je.chatting._v1_webSocket.domain.ChatRoom;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

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
}
