package com.Shoots.controller;

import com.Shoots.domain.PostComment;
import com.Shoots.service.PostCommentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RestController
@RequestMapping(value="/comment")
public class PostCommentController {

    private static final Logger logger = LoggerFactory.getLogger(PostCommentController.class);

    private PostCommentService postCommentService;

    public PostCommentController(PostCommentService postCommentService) {
        this.postCommentService = postCommentService;
    }

    @PostMapping(value="/list")
    public Map<String, Object> CommentList(int post_idx, int state) {
        List<PostComment> list = postCommentService.getCommentList(post_idx, state);
        int listcount = postCommentService.getListCount(post_idx);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("list", list);
        map.put("listcount", listcount);
        map.put("state", state);
        logger.info("/comment/list");
        return map;
    }


    @PostMapping(value = "/add")
    public int commentAdd(PostComment co) {
        return postCommentService.commentsInsert(co);
    }


    @PostMapping(value = "/update")
    public int commentUpdate(PostComment co) {
        return postCommentService.commentsUpdate(co);
    }

    @PostMapping(value = "/delete")
    public int commentDelete(int num) {
        return postCommentService.commentsDelete(num);
    }


}
