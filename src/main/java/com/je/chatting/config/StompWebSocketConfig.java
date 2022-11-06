package com.je.chatting.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@RequiredArgsConstructor
@EnableWebSocketMessageBroker   // STOMP 활성화
public class StompWebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/stomp/je-chat")     // Endpoint
                .setAllowedOrigins("*");                 // 허용 도메인
    }

    // app 내부에서 사용할 path 지정
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // 메시지 전송(발송) 요청 path prefix
        registry.setApplicationDestinationPrefixes("/pub");              // /kafka
        // 메시지 수신(구독) 요청 path prefix
        registry.enableSimpleBroker("/sub");    // /topic
    }
}