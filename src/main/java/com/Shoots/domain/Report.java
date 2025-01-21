package com.Shoots.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Report {
    private int reportIdx;
    private int reporterUser;
    private String reportedUser;
    private String category;
    private String PostIdx;
    private String CommentIdx;
}
