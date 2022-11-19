package com.je.chatting._v2_stomp.service;

import com.je.chatting._v2_stomp.domain.StompChatRoom;
import com.je.chatting._v2_stomp.domain.StompChatRoomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class StompChatRoomService {

    private final StompChatRoomRepository chatRoomRepository;

    /* 채팅방 전체 목록 조회 */
    public List<StompChatRoom> findAllStompRoom() {
        return chatRoomRepository.findAllRoom();
    }

    /* 채팅방 번호로 조회 */
    public StompChatRoom findStompRoomById(String roomId) {
        return chatRoomRepository.findRoomById(roomId);
    }

    /* 채팅방 생성 */
    public StompChatRoom createStompRoom(String name) {
        return chatRoomRepository.createRoom(name);
    }
}
