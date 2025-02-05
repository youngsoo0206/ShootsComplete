DROP TABLE IF EXISTS chatRoom;
CREATE TABLE chat_room (
    chatRoom_idx INT AUTO_INCREMENT PRIMARY KEY,
    Title varchar(20) DEFAULT 'new_ChatRoom',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);