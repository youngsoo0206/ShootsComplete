CREATE TABLE post_comment (
          comment_idx INT AUTO_INCREMENT PRIMARY KEY, -- 댓글 식별 번호
          post_idx INT, -- 게시글 아이디
          comment_ref_id INT, -- 부모 댓글 아이디 (자기참조)
          writer INT, -- 작성자
          content TEXT NOT NULL, -- 내용
          register_date DATETIME DEFAULT CURRENT_TIMESTAMP, -- 등록일
          FOREIGN KEY (post_idx) REFERENCES post(post_idx) ON DELETE CASCADE, -- 게시글 아이디 외래키
          FOREIGN KEY (comment_ref_id) REFERENCES post_comment(comment_idx) ON DELETE CASCADE, -- 부모 댓글 아이디 외래키
          FOREIGN KEY (writer) REFERENCES regular_user(idx) ON DELETE CASCADE -- 작성자 외래키
);




drop table post_comment;

