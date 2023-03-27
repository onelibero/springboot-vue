package com.comic.springbootproject.common.vo;

import lombok.Data;

/**
 * @description: Search
 * @program: shixun1
 * @date: 2020-12-29 15:48
 */
@Data
public class Search {
    // 当前页
    private int currentPage;
    //页长
    private int pageSize;
    //排序字段
    private String sort;
    //排序方式
    private String direction;
    //关键字
    private String keyword;

    public void initSearch() {
        if (currentPage == 0) {
            currentPage = 1;
        }
        if (pageSize == 0) {
            pageSize = 5;
        }
    }
}
