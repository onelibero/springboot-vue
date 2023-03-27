package com.comic.springbootproject.user.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "user_history")
public class UserHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int userId;
    private int comicId;

    private String createTime;

    public UserHistory(int userId, int comicId,String createTime) {
        this.userId = userId;
        this.comicId = comicId;
        this.createTime = createTime;
    }

    public UserHistory() {
    }
}
