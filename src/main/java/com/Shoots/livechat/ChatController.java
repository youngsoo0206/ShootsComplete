package com.Shoots.livechat;

import com.Shoots.domain.chat_room_log;
import com.Shoots.service.chat_service;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@AllArgsConstructor
@RequestMapping(value="/livechat")
public class ChatController {

    private static Logger logger = LoggerFactory.getLogger(ChatController.class);
    chat_service chatService;

    @GetMapping(value = "/livechat")
    public String livechat(HttpSession session, Model model) {
        model.addAttribute("id", session.getId());
        return "livechat/livechat";
    }

    @ResponseBody
    @PostMapping(value = "/makelog")
    public Map<String, Object> makelog(@RequestBody chat_room_log room_log) {
        logger.info(room_log.toString());

        Map<String, Object> resp = new HashMap<>();
        Map<String, Object> map = new HashMap<>();
        map.put("chat_room_idx", room_log.getChat_room_idx());
        map.put("content", room_log.getContent());
        map.put("sender", room_log.getSender());
        chatService.insert_chat_log(map);

        resp.put("status", "success");
        return resp;
    }

    @ResponseBody
    @PostMapping(value = "/getlog")
    public List<chat_room_log> getlog(@RequestBody chat_room_log room_log) {
        logger.info(room_log.toString());

        Map<String, Object> map = new HashMap<>();
        map.put("chat_room_idx", room_log.getChat_room_idx());

        return chatService.get_chat_log(map);
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
