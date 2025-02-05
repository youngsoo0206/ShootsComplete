DROP TABLE IF EXISTS chat_room_user;
CREATE TABLE chat_room_user (
    chat_room_idx BIGINT NOT NULL,
    user_idx BIGINT NOT NULL,
    joined_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (chat_room_idx, user_idx),
    FOREIGN KEY (chat_room_idx) REFERENCES chat_room(chat_room_idx) ON DELETE CASCADE,
    FOREIGN KEY (user_idx) REFERENCES regular_user(idx) ON DELETE CASCADE
);