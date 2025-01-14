CREATE TABLE post_comment (
              comment_idx INT PRIMARY KEY AUTO_INCREMENT,             -- 댓글 식별 번호 (자동 증가)
              post_idx INT NOT NULL,                                  -- 게시글 식별 번호
              comment_ref_id INTEGER DEFAULT NULL,                         -- 부모 댓글 아이디 (자기참조)
              writer INT NOT NULL,                                     -- 작성자(regular_user 테이블 참조)
              content TEXT NOT NULL,                                   -- 내용
              register_date DATETIME DEFAULT CURRENT_TIMESTAMP,        -- 등록일
-- 외래 키 제약 조건
              CONSTRAINT fk_post FOREIGN KEY (post_idx) REFERENCES post(post_idx) ON DELETE CASCADE,
              CONSTRAINT fk_comment_ref FOREIGN KEY (comment_ref_id) REFERENCES post_comment(comment_idx) ON DELETE CASCADE,
              CONSTRAINT fk_writer_post_comment FOREIGN KEY (writer) REFERENCES regular_user(idx) ON DELETE CASCADE
);




drop table post_comment;

SELECT * FROM information_schema.key_column_usage
WHERE table_name = 'post_comment';


insert into POST_COMMENT
(post_idx, comment_ref_id, writer, content)
values( 34, null, 1,'ㅎㅇ');

insert into POST_COMMENT
(post_idx, comment_ref_id, writer, content)
values( 34, null, 1,'comment_idx 값 확인하기');