package com.je.chatting._v3_kafka;

import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.*;

@Repository
public class KafkaChatRoomRepository {

    private Map<String, KafkaChatRoom> chatRoomMap;

    @PostConstruct
    private void init() {
        chatRoomMap = new LinkedHashMap<>();
    }

    /* 채팅방 전체 목록 조회 */
    public List<KafkaChatRoom> findAllRoom() {
        return new ArrayList<>(chatRoomMap.values());
    }

    /* 채팅방 번호로 조회 */
    public KafkaChatRoom findRoomById(String roomId) {
        return chatRoomMap.get(roomId);
    }

    /* 채팅방 생성 */
    public KafkaChatRoom createRoom(String name) {
        KafkaChatRoom chatRoom = new KafkaChatRoom(name);
        chatRoomMap.put(chatRoom.getRoomId(), chatRoom);
        return chatRoom;
    }
}
