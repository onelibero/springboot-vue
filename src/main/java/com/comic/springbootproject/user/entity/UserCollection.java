package com.comic.springbootproject.user.entity;

import lombok.Data;
import lombok.Generated;

import javax.persistence.*;

@Data
@Entity
@Table(name = "user_collection")
public class UserCollection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int userId;
    private int comicId;

    public UserCollection(int userId, int comicId) {
        this.userId = userId;
        this.comicId = comicId;
    }

    public UserCollection() {
    }
}
