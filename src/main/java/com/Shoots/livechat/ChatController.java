package com.Shoots.livechat;

import jakarta.servlet.http.HttpSession;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value="/livechat")
public class ChatController {

    @GetMapping(value = "/livechat")
    public String livechat(HttpSession session, Model model) {
        model.addAttribute("id", session.getId());
        return "livechat/livechat";
    }

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/{topicName}")
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage,
                                   @DestinationVariable String topicName, HttpSession session) {
        return chatMessage;
    }

    @MessageMapping("/chat.addUser")
    @SendTo("/topic/{topicName}")
    public ChatMessage addUser(@Payload ChatMessage chatMessage,
            SimpMessageHeaderAccessor headerAccessor,
                               @DestinationVariable String topicName, HttpSession session) {
        // Add username in web socket session
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
        return chatMessage;
    }



}
