package com.je.chatting._v3_kafka.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@RequiredArgsConstructor
@EnableWebSocketMessageBroker   // STOMP 활성화
public class KafkaWebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/kafka/je-chat")     // Endpoint
                .setAllowedOriginPatterns("*").withSockJS();                 // 허용 도메인
    }

    // app 내부에서 사용할 path 지정
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // 메시지 송신(발행) 요청 path prefix
        registry.setApplicationDestinationPrefixes("/pub");
        // 메시지 수신(구독) 요청 path prefix
        registry.enableSimpleBroker("/topic");
    }
}
