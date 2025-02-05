package com.Shoots.service;

import com.Shoots.domain.chat_room;
import com.Shoots.mybatis.mapper.chat_mapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
@Service
@AllArgsConstructor
public class chat_serviceImpl implements chat_service {

    private chat_mapper dao;

    @Override
    public int create_chat_room() {
        chat_room chatRoom = new chat_room();
        dao.make_chat_room(chatRoom); // 채팅방 생성. chatRoom domain에 매핑
        return chatRoom.getChat_room_idx(); 
    }
}
