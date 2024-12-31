package com.Shoots.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaginationResult {

    private int  maxpage;
        private int  startpage;
        private int  endpage;

    public PaginationResult(int page, int limit, int listcount) {

        int maxpage = (listcount + limit - 1) / limit;
        int startpage = ((page - 1) / 10) * 10 + 1;
        int endpage = startpage + 10 - 1;

        if (endpage > maxpage) {
            endpage = maxpage;
        }

        this.maxpage = maxpage;
        this.startpage = startpage;
        this.endpage = endpage;
    }
}
