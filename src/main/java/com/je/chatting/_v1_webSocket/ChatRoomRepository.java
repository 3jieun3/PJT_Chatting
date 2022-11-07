package com.je.chatting._v1_webSocket;

import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.*;

@Repository
public class ChatRoomRepository {

    private Map<String, ChatRoom> chatRoomMap;  // 모든 채팅방 정보

    @PostConstruct
    private void init() {
        chatRoomMap = new LinkedHashMap<>();
    }

    /* 채팅방 전체 목록 최신순 조회 */
    public List<ChatRoom> findAllRoom() {
        List<ChatRoom> chatRoomList =  new ArrayList<>(chatRoomMap.values());
        Collections.reverse(chatRoomList);
        return chatRoomList;
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
}
