package com.je.chatting._v3_kafka.service;

import com.je.chatting._v3_kafka.domain.KafkaChatRoom;
import com.je.chatting._v3_kafka.domain.KafkaChatRoomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class KafkaChatRoomService {

    private final KafkaChatRoomRepository kafkaChatRoomRepository;

    /* 채팅방 전체 목록 조회 */
    public List<KafkaChatRoom> findAllKafkaRoom() {
        return kafkaChatRoomRepository.findAllRoom();
    }
    /* 채팅방 번호로 조회 */
    public KafkaChatRoom findKafkaRoomById(String roomId) {
        return kafkaChatRoomRepository.findRoomById(roomId);
    }
    /* 채팅방 생성 */
    public KafkaChatRoom createKafkaRoom(String name) {
        return kafkaChatRoomRepository.createRoom(name);
    }
    /* 채팅방 이름 중복 체크 */
    public Boolean isDuplicatedName(String name) {
        return kafkaChatRoomRepository.findAllRoom().stream().anyMatch(r -> r.getName().equals(name));
    }
}
