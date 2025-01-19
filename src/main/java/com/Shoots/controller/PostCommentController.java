package com.Shoots.controller;

import com.Shoots.domain.PostComment;
import com.Shoots.service.PostCommentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    public Map<String, Object> CommentList(@RequestParam("post_idx") int post_idx,@RequestParam("state") int state) {
        List<PostComment> list = postCommentService.getCommentList(post_idx, state);

        int listcount = postCommentService.getListCount(post_idx);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("commentlist", list);
        map.put("listcount", listcount);
        map.put("state", state);
        logger.info("/comment/list");
        return map;
    }

//    // 비밀댓글 필터링: 비밀댓글은 작성자와 댓글 작성자만 볼 수 있음
//    private List<PostComment> filterSecretComments(List<PostComment> comments, int userId) {
//        return comments.stream()
//                .filter(comment -> !comment.isSecret() || comment.getWriter() == userId) // 비밀댓글인 경우 작성자만 보기
//                .collect(Collectors.toList());
//    }


    @PostMapping(value = "/add")
    public int commentAdd(PostComment co) {
        return postCommentService.commentsInsert(co);
    }

    @PostMapping(value = "/reply")
    public int commentReply(PostComment co) {
        return postCommentService.commentsReply(co);
    }

    @PostMapping(value = "/update")
    public int commentUpdate(PostComment co) {
        return postCommentService.commentsUpdate(co);
    }

    @PostMapping(value = "/delete")
    public int commentDelete(int comment_idx) {
        return postCommentService.commentsDelete(comment_idx);
    }



}
