package com.Shoots.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Report {
    private int reportIdx;
    private String reporterUser;
    private String reportedUser;
    private String category;
    private String PostIdx;
    private String CommentIdx;
}
