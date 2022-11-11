package com.je.chatting._v3_kafka;

import lombok.Getter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
public class KafkaChatMessage implements Serializable {

    private String sender;          // 보내는 사람

    private String content;         // 내용

    private final LocalDateTime sendAt = LocalDateTime.now();   // 전송 시각

    @Override
    public String toString() {
        return String.format("%s : %s [%s]", sender, content,
                sendAt.format(DateTimeFormatter.ofPattern("yy-MM-dd(EEE) hh:mm:ss a")));
    }
}
