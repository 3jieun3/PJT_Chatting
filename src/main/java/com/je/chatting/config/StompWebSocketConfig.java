//package com.je.chatting.config;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.messaging.simp.config.MessageBrokerRegistry;
//import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
//import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
//import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
//
//
//@Slf4j
//@Configuration
//@EnableWebSocketMessageBroker   // STOMP 활성화
//public class StompWebSocketConfig implements WebSocketMessageBrokerConfigurer {
//
//    @Override
//    public void registerStompEndpoints(StompEndpointRegistry registry) {
//        registry.addEndpoint("/je-chat")    //
//                .setAllowedOrigins("*")            // 허용 도메인
//                .withSockJS();
//    }
//
//    // app 내부에서 사용할 path 지정
//    @Override
//    public void configureMessageBroker(MessageBrokerRegistry registry) {
//        // (pub) 메시지 전송(발송) 시 사용할 path
//        registry.setApplicationDestinationPrefixes("/pub");     // /kafka
//        // (sub) 메시지 수신(구독) 시 사용할 path
//        registry.enableSimpleBroker("/sub");    // /topic/
//    }
//}