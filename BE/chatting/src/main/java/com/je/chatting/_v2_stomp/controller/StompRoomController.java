package com.je.chatting._v2_stomp.controller;

import com.je.chatting._v2_stomp.domain.StompChatRoom;
import com.je.chatting._v2_stomp.service.StompChatRoomService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/stomp/chat")
public class StompRoomController {

    private final StompChatRoomService stompChatService;

    /* 채팅방 목록 조회 */
    @GetMapping("/rooms")
    public List<StompChatRoom> getStompRooms() {
        log.info("Getting ChatRoom List");
        return stompChatService.findAllStompRoom();
    }

    /* 채팅방 조회 */
    @GetMapping("/room/{roomId}")
    public StompChatRoom getStompRoom(@PathVariable String roomId) {
        log.info("Getting ChatRoom {}", roomId);
        return stompChatService.findStompRoomById(roomId);
    }

    /* 채팅방 생성 */
    @PostMapping("/room")
    public StompChatRoom createStompRoom(@RequestParam String name) {
        log.info("ChatRoom created : {}", name);
        return stompChatService.createStompRoom(name);
    }

}
