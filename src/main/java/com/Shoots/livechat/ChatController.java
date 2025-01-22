package com.Shoots.livechat;

import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static Logger logger = LoggerFactory.getLogger(ChatController.class);

    @GetMapping(value = "/livechat")
    public String livechat(HttpSession session, Model model) {
        model.addAttribute("id", session.getId());
        return "livechat/livechat";
    }

    @MessageMapping("/chat{topicName}")
    @SendTo("/topic/{topicName}")
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage,
                                   @DestinationVariable String topicName) {
        return chatMessage;
    }

    @MessageMapping("{topicName}")
    @SendTo("/topic/{topicName}")
    public ChatMessage addUser(@Payload ChatMessage chatMessage,
                                SimpMessageHeaderAccessor headerAccessor,
                               @DestinationVariable String topicName) {
        // Add username in web socket session
        logger.info("topicName : " +  topicName);
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
        return chatMessage;
    }



}
