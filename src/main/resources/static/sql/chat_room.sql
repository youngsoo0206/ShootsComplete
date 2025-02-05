DROP TABLE IF EXISTS chat_room_log;
DROP TABLE IF EXISTS chat_participants;
DROP TABLE IF EXISTS chat_room;
CREATE TABLE chat_room
(
    chat_room_idx INT AUTO_INCREMENT PRIMARY KEY,
    created_at    TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
CREATE TABLE chat_participants
(
    chat_room_idx INT NOT NULL,
    user_idx      INT NOT NULL,
    joined_at     TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (chat_room_idx, user_idx),
    FOREIGN KEY (chat_room_idx) REFERENCES chat_room (chat_room_idx) ON DELETE CASCADE,
    FOREIGN KEY (user_idx) REFERENCES regular_user (idx) ON DELETE CASCADE
);
CREATE TABLE chat_room_log
(
    chat_room_log_idx INT AUTO_INCREMENT PRIMARY KEY,
    chat_room_idx     INT NOT NULL,
    content           text,
    sender            text,
    sent_at           TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (chat_room_idx) REFERENCES chat_room (chat_room_idx) ON DELETE CASCADE
);
