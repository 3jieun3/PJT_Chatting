package com.je.chatting.controller;

import com.je.chatting.domain.ChatRoom;
import com.je.chatting.service.ChatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/chat")
public class ChatController {

    private final ChatService chatService;

    /* 채팅방 목록 조회 */
    @GetMapping
    public List<ChatRoom> findAllRoom() {
        log.info("Getting ChatRoom List");
        return chatService.findAllRoom();
    }

    /* 채팅방 생성 */
    @PostMapping
    public ChatRoom createRoom(@RequestParam String name) {
        log.info("ChatRoom created. : {}", name);
        return chatService.createRoom(name);
    }

}
