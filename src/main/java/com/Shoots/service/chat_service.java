package com.Shoots.service;

import com.Shoots.domain.chat_room_log;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

public interface chat_service {
    public int create_chat_room();
    public void insert_chat_log(Map<String,Object> map);
    public List<chat_room_log> get_chat_log(Map<String, Object> map);
}
