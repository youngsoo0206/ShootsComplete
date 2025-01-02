CREATE TABLE post (
                      post_idx INT AUTO_INCREMENT PRIMARY KEY, -- 게시글 식별 번호 (글번호)
                      writer INT NOT NULL, -- 작성자 (regular_user 테이블 참조)
                      category CHAR(1) NOT NULL CHECK (category IN ('A', 'B')), -- 글 종류
                      title VARCHAR(100) NOT NULL, -- 제목
                      content TEXT NOT NULL, -- 내용
                      post_file VARCHAR(50), -- 첨부파일
                      price INT, -- 가격
                      register_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- 등록일
                      readcount INT DEFAULT 0, -- 조회수
                      CONSTRAINT fk_writer FOREIGN KEY (writer) REFERENCES regular_user(idx) ON DELETE CASCADE
);


INSERT INTO post (writer, category, title, content, price, readcount)
VALUES (1, 'A', '테스트 게시글 제목', '이것은 테스트 게시글 내용입니다.', 100.00, 0);


drop table post;