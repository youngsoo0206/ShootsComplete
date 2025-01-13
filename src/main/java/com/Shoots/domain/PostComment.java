package com.Shoots.domain;

public class PostComment {
    private int comment_idx;
    private int post_idx;
    private Integer comment_ref_id;
    private int writer;
    private String content;
    private String register_date;
    private String user_id;
    private int idx;
    private String user_file;
    private String post_title;
    private String category;

    public int getComment_idx() {
        return comment_idx;
    }

    public void setComment_idx(int comment_idx) {
        this.comment_idx = comment_idx;
    }

    public int getPost_idx() {
        return post_idx;
    }

    public void setPost_idx(int post_idx) {
        this.post_idx = post_idx;
    }

    public Integer getComment_ref_id() {
        return comment_ref_id;
    }

    public void setComment_ref_id(Integer comment_ref_id) {
        this.comment_ref_id = comment_ref_id;
    }

    public int getWriter() {
        return writer;
    }

    public void setWriter(int writer) {
        this.writer = writer;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getRegister_date() {
        return register_date;
    }

    public void setRegister_date(String register_date) {
        this.register_date = register_date;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public int getIdx() {
        return idx;
    }

    public void setIdx(int idx) {
        this.idx = idx;
    }

    public String getUser_file() {
        return user_file;
    }

    public void setUser_file(String user_file) {
        this.user_file = user_file;
    }

    public String getPost_title() {
        return post_title;
    }

    public void setPost_title(String post_title) {
        this.post_title = post_title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
