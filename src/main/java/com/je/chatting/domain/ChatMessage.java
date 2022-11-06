package com.je.chatting.domain;

import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
public class ChatMessage implements Serializable {

    private MessageType type;       // 메시지 타입

    private String roomId;          // 채팅방 번호 (topic 역할)

    private String sender;          // 보내는 사람

    private String content;         // 내용

    private final LocalDateTime sendAt = LocalDateTime.now();   // 전송 시각

    public enum MessageType {
        JOIN, CHAT, LEAVE           // 메시지 타입 : 입장, 채팅, 퇴장
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return String.format("%s : %s [%s/%s]", sender, content, type,
                sendAt.format(DateTimeFormatter.ofPattern("yy-MM-dd(EEE) hh:mm:ss a")));
    }
}
