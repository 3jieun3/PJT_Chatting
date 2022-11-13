package com.je.chatting._v1_webSocket;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.*;

@Configuration
@RequiredArgsConstructor
@EnableWebSocket    // 웹 소켓 활성화
public class WebSocketConfig implements WebSocketConfigurer {

    private final WebSocketChatHandler webSocketChatHandler;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(webSocketChatHandler, "/je-chat")       // 웹소켓 연결 endpoint 를 /je-chat 로 설정
                .setAllowedOrigins("*");                            // CORS 설정
    }
}
