package com.je.chatting._v1_webSocket.controller;

import com.je.chatting._v1_webSocket.sevice.ChatService;
import com.je.chatting._v1_webSocket.domain.ChatRoom;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/chat")
public class RoomController {

    private final ChatService chatService;

    /* 채팅방 목록 조회 */
    @GetMapping("/rooms")
    public List<ChatRoom> getRooms() {
        log.info("Getting ChatRoom List");
        return chatService.findAllRoom();
    }

    /* 채팅방 조회 */
    @GetMapping("/room/{roomId}")
    public ChatRoom getRoom(@PathVariable String roomId) {
        log.info("Getting ChatRoom {}", roomId);
        return chatService.findRoomById(roomId);
    }

    /* 채팅방 생성 */
    @PostMapping("/room")
    public ChatRoom createRoom(@RequestParam String name) {
        log.info("ChatRoom created : {}", name);
        return chatService.createRoom(name);
    }

}
