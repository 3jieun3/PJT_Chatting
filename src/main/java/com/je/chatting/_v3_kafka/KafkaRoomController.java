package com.je.chatting._v3_kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/kafka/chat")
public class KafkaRoomController {

    private final KafkaChatRoomService kafkaChatRoomService;

    /* 채팅방 목록 조회 */
    @GetMapping("/rooms")
    public List<KafkaChatRoom> getStompRooms() {
        log.info("Getting ChatRoom List");
        return kafkaChatRoomService.findAllKafkaRoom();
    }

    /* 채팅방 조회 */
    @GetMapping("/room/{roomId}")
    public KafkaChatRoom getStompRoom(@PathVariable String roomId) {
        log.info("Getting ChatRoom {}", roomId);
        return kafkaChatRoomService.findKafkaRoomById(roomId);
    }

    /* 채팅방 생성 */
    @PostMapping("/room")
    public KafkaChatRoom createStompRoom(@RequestParam String name) {
        log.info("ChatRoom created : {}", name);
        return kafkaChatRoomService.createKafkaRoom(name);
    }

    /* 채팅방 이름 중복 체크 */
    @GetMapping("/room/dup_check")
    public Boolean isDuplicatedName(@RequestParam String name) {
        log.info("ChatRoom Name Duplication Check : {}", name);
        return kafkaChatRoomService.isDuplicatedName(name);
    }

}
