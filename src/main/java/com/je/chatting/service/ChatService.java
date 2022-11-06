package com.je.chatting.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.je.chatting.domain.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.*;

@Slf4j
@RequiredArgsConstructor
@Service
public class ChatService {

    private final ObjectMapper objectMapper;
    private Map<String, ChatRoom> chatRoomMap;  // 모든 채팅방 정보

    @PostConstruct
    private void init() {
        chatRoomMap = new LinkedHashMap<>();
    }

    /* 채팅방 전체 목록 조회 */
    public List<ChatRoom> findAllRoom() {
        return new ArrayList<>(chatRoomMap.values());
    }

    /* 채팅방 번호로 조회 */
    public ChatRoom findRoomById(String roomId) {
        return chatRoomMap.get(roomId);
    }

    /* 채팅방 생성 */
    public ChatRoom createRoom(String name) {
        ChatRoom chatRoom = new ChatRoom(name);
        chatRoomMap.put(chatRoom.getRoomId(), chatRoom);
        return chatRoom;
    }

    /* 메시지 전송 : 지정한 Web Socket session 에 메시지 전송 */
    public <T> void sendMessage(WebSocketSession session, T message) {
        try {
            session.sendMessage(new TextMessage(objectMapper.writeValueAsString(message)));
        } catch (IOException e) {
            e.printStackTrace();
            log.error(e.getMessage(), e);
        }
    }
}
