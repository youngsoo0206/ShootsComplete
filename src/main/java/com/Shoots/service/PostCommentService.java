package com.Shoots.service;


import com.Shoots.domain.PostComment;
import jakarta.servlet.http.HttpSession;

import java.util.List;

public interface PostCommentService {

    // 글의 갯수 구하기
    public int getListCount(int post_idx);

    // 댓글 목록 가져오기
    public List<PostComment> getCommentList(int post_idx, int state);



    // 댓글 등록하기
    public int commentsInsert(PostComment c);

    // 댓글 삭제
    public int commentsDelete(int num);

    // 댓글 수정
    public int commentsUpdate(PostComment co);

    int commentsReply(PostComment co);
}
