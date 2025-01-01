package com.Shoots.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
public class Match {
    private long match_idx;
    private long writer;
    private LocalDate match_date;
    private LocalTime match_time;
    private int player_max;
    private int player_min;
    private String player_gender;
    private int price;
    private LocalDateTime register_date;
}
