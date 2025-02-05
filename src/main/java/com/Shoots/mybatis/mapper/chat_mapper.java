package com.Shoots.mybatis.mapper;

import com.Shoots.domain.chat_room;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface chat_mapper {
    void make_chat_room(chat_room chat_room);
}
