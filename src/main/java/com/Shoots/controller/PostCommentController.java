//package com.Shoots.controller;
//
//import com.Shoots.domain.PostComment;
//import com.Shoots.service.PostCommentService;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@Controller
//@RestController
//@RequestMapping(value="/comment")
//public class PostCommentController {
//
//    private static final Logger logger = LoggerFactory.getLogger(PostCommentController.class);
//
//    private PostCommentService commentService;
//
//    public PostCommentController(PostCommentService commentService) {
//        this.commentService = commentService;
//    }
//
//    @PostMapping(value="/list")
//    public Map<String, Object> CommentList(int post_idx, int page) {
//        List<PostComment> list = commentService.getCommentList(post_idx, page);
//        int listcount = commentService.getListCount(post_idx);
//        Map<String, Object> map = new HashMap<String, Object>();
//        map.put("list", list);
//        map.put("listcount", listcount);
//        logger.info("/comment/list");
//        return map;
//    }
//
//    @PostMapping(value = "/add")
//    public int commentAdd(PostComment co) {
//        return commentService.commentsInsert(co);
//    }
//
//
//    @PostMapping(value = "/update")
//    public int commentUpdate(PostComment co) {
//        return commentService.commentsUpdate(co);
//    }
//
//    @PostMapping(value = "/delete")
//    public int commentDelete(int num) {
//        return commentService.commentsDelete(num);
//    }
//
//}
