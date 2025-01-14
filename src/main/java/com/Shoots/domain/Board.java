package com.Shoots.domain;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class Board {
    private int board_idx;
    private int writer;
    private String title;
    private String content;
    private String register_date;
    private int readcount;
    private String user_id;
    private int idx;
}
