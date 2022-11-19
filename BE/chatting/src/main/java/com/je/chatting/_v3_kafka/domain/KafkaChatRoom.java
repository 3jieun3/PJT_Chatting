package com.je.chatting._v3_kafka.domain;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

@Slf4j
@Getter
public class KafkaChatRoom {

    private final String roomId;  // 채팅방 번호

    private final String name;    // 채팅방 이름 (중복 불가능)

    public KafkaChatRoom(String name) {
        this.roomId = UUID.randomUUID().toString();
        this.name = name;
    }
}
