package com.comic.springbootproject.comic.entity;


import com.comic.springbootproject.common.entiy.AbstractEntity;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
public class Comic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String comicName;//名称
    private String nickName;//昵称
    private String region;//地区
    private String remark;
    private String label;//标签
    private String year;//上映年
    private String updateTime;//上映时间
    private String description;//详情
    private String cover;//封面
    private int number;//集数
    private int popularity;//热度

    private String url;//类型

    public Comic() {
    }

    public Comic(String label,int id, String comicName, String nickName, String region, String remark, String year, String updateTime, String description, String cover, int number, int popularity, String url) {
        this.id = id;
        this.comicName = comicName;
        this.nickName = nickName;
        this.region = region;
        this.remark = remark;
        this.year = year;
        this.updateTime = updateTime;
        this.description = description;
        this.cover = cover;
        this.number = number;
        this.popularity = popularity;
        this.url = url;
        this.label = label;
    }
}
