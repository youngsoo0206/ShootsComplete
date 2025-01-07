CREATE TABLE post (
                      post_idx INT AUTO_INCREMENT PRIMARY KEY, -- 게시글 식별 번호 (글번호)
                      writer INT NOT NULL, -- 작성자 (regular_user 테이블 참조)
                      category CHAR(1) NOT NULL CHECK (category IN ('A', 'B')), -- 글 종류
                      title VARCHAR(100) NOT NULL, -- 제목
                      content TEXT NOT NULL, -- 내용
                      post_file VARCHAR(50), -- 첨부파일 실제 저장된 파일이름
                      post_original VARCHAR(50), -- 첨부파일 첨부될 파일 명
                      price INTEGER DEFAULT 0, -- 가격
                      register_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- 등록일
                      readcount INT DEFAULT 0, -- 조회수
                      CONSTRAINT fk_writer FOREIGN KEY (writer) REFERENCES regular_user(idx) ON DELETE CASCADE
);




drop table post;


select *
from post
where category = 'A';


SELECT p.post_idx, p.writer, p.category, p.title, p.content, p.post_file, p.post_original, p.price, p.register_date, p.readcount, r.user_id
FROM post p
    INNER JOIN regular_user r ON p.writer = r.idx
WHERE p.category
ORDER BY p.register_date DESC;