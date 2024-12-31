package com.Shoots.livechat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {


    private SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/chat.sendMessage") // 클라이언트에서 "/app/chat.sendMessage"로 전송된 메시지를 처리
    public void sendMessage(ChatMessage message) {
        // 메시지 전송: "/topic/public"으로 메시지를 브로드캐스트
        messagingTemplate.convertAndSend("/topic/public", message);
    }
}
