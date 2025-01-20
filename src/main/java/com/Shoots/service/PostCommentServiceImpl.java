package com.Shoots.service;

import com.Shoots.domain.PostComment;
import com.Shoots.mybatis.mapper.PostCommentMapper;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PostCommentServiceImpl implements PostCommentService {

    private PostCommentMapper dao;

    public PostCommentServiceImpl(PostCommentMapper dao) {

        this.dao = dao;
    }

    @Override
    public int getListCount(int post_idx) {
        return dao.getListCount(post_idx);
    }



    @Override
    public List<PostComment> getCommentList(int post_idx, int state) {

        int startrow = 1;
        int endrow=state*3;

        Map<String,Integer> map = new HashMap<String,Integer>();
        map.put("post_idx", post_idx);
        map.put("state", state);
        map.put("start", startrow);
        map.put("end", endrow);
        return dao.getCommentList(map);
    }



    @Override
    public int commentsInsert(PostComment c) {
        return dao.commentsInsert(c);
    }

    @Override
    public int commentsReply(PostComment c) {
        return dao.commentsReply(c);
    }



    @Override
    public int commentsUpdate(PostComment co) {
        return dao.commentsUpdate(co);
    }

    @Override
    public int commentsDelete(int num) {
        return dao.commentsDelete(num);
    }
}
