CREATE TABLE post_comment (
              comment_idx INT PRIMARY KEY AUTO_INCREMENT,             -- 댓글 식별 번호 (자동 증가)
              post_idx INT NOT NULL,                                  -- 게시글 식별 번호
              comment_ref_id INTEGER DEFAULT NULL,                         -- 부모 댓글 아이디 (자기참조)
              writer INT NOT NULL,                                     -- 작성자(regular_user 테이블 참조)
              content TEXT NOT NULL,                                   -- 내용
              register_date DATETIME DEFAULT CURRENT_TIMESTAMP,        -- 등록일
              is_secret CHAR(1) DEFAULT 'N' CHECK (is_secret IN ('Y', 'N')), -- 비밀 댓글 여부
-- 외래 키 제약 조건
              CONSTRAINT fk_post FOREIGN KEY (post_idx) REFERENCES post(post_idx) ON DELETE CASCADE,
              CONSTRAINT fk_comment_ref FOREIGN KEY (comment_ref_id) REFERENCES post_comment(comment_idx) ON DELETE CASCADE,
              CONSTRAINT fk_writer_post_comment FOREIGN KEY (writer) REFERENCES regular_user(idx) ON DELETE CASCADE
);

# is_secret CHAR(1) DEFAULT 'N' CHECK (is_secret IN ('Y', 'N')), -- 비밀 댓글 여부

ALTER TABLE post_comment
    ADD is_secret CHAR(1) DEFAULT 'N' CHECK (is_secret IN ('Y', 'N'));


drop table post_comment;

SELECT * FROM information_schema.key_column_usage
WHERE table_name = 'post_comment';
