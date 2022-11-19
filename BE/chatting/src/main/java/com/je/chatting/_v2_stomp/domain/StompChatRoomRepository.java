package com.je.chatting._v2_stomp.domain;

import com.je.chatting._v2_stomp.domain.StompChatRoom;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.*;

@Repository
public class StompChatRoomRepository {

    private Map<String, StompChatRoom> chatRoomMap;

    @PostConstruct
    private void init() {
        chatRoomMap = new LinkedHashMap<>();
    }

    /* 채팅방 전체 목록 최신순 조회 */
    public List<StompChatRoom> findAllRoom() {
        List<StompChatRoom> chatRoomList =  new ArrayList<>(chatRoomMap.values());
        Collections.reverse(chatRoomList);
        return chatRoomList;
    }

    /* 채팅방 번호로 조회 */
    public StompChatRoom findRoomById(String roomId) {
        return chatRoomMap.get(roomId);
    }

    /* 채팅방 생성 */
    public StompChatRoom createRoom(String name) {
        StompChatRoom chatRoom = new StompChatRoom(name);
        chatRoomMap.put(chatRoom.getRoomId(), chatRoom);
        return chatRoom;
    }
}
