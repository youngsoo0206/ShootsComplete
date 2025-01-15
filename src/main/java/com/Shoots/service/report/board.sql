DROP TABLE IF EXISTS board;

CREATE TABLE board (
    board_idx INT AUTO_INCREMENT PRIMARY KEY,
    readcount INT DEFAULT 0,
    writer varchar(100) NOT NULL,
    title varchar(100) NOT NULL,
    content TEXT NOT NULL,
    register_date TIMESTAMP default CURRENT_TIMESTAMP
);
#Not NUll , CHECK (category IN ('A', 'B')), DEFAULT 0

select *
from board;
