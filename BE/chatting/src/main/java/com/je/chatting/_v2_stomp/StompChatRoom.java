package com.je.chatting._v2_stomp;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

@Slf4j
@Getter
public class StompChatRoom {

    private final String roomId;  // 채팅방 번호

    private final String name;    // 채팅방 이름

    public StompChatRoom(String name) {
        this.roomId = UUID.randomUUID().toString();
        this.name = name;
    }
}