CREATE TABLE regular_user (
                              idx BIGINT AUTO_INCREMENT PRIMARY KEY, -- 사용자 식별 번호
                              user_idx VARCHAR(30) NOT NULL, -- 사용자 ID
                              password VARCHAR(20) NOT NULL, -- 비밀번호
                              name VARCHAR(20) NOT NULL, -- 이름
                              jumin INT NOT NULL, -- 주민번호 앞자리
                              gender TINYINT NOT NULL CHECK (gender IN (1, 2, 3, 4)), -- 성별 (1, 2, 3, 4만 허용)
                              tel VARCHAR(15) NOT NULL, -- 전화번호
                              email VARCHAR(30) NOT NULL, -- 이메일
                              nickname VARCHAR(20), -- 닉네임
                              user_file VARCHAR(50), -- 사용자 파일
                              register_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- 가입일
                              role VARCHAR(10) NOT NULL DEFAULT 'common' -- 역할
);

INSERT INTO regular_user (user_idx, password, name, jumin, gender, tel, email, nickname, user_file)
VALUES ('testuser', 'password123', '홍길동', 123456789, 1, '010-1234-5678', 'testuser@example.com', '홍길동', 'profile.jpg');
