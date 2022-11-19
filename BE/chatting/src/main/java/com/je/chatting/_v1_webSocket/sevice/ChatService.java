package com.je.chatting._v1_webSocket.sevice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.je.chatting._v1_webSocket.domain.ChatRoomRepository;
import com.je.chatting._v1_webSocket.domain.ChatRoom;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class ChatService {

    private final ObjectMapper objectMapper;
    private final ChatRoomRepository chatRoomRepository;

    /* 채팅방 전체 목록 조회 */
    public List<ChatRoom> findAllRoom() {
        return chatRoomRepository.findAllRoom();
    }

    /* 채팅방 번호로 조회 */
    public ChatRoom findRoomById(String roomId) {
        return chatRoomRepository.findRoomById(roomId);
    }

    /* 채팅방 생성 */
    public ChatRoom createRoom(String name) {
        return chatRoomRepository.createRoom(name);
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
