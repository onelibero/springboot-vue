package com.comic.springbootproject.user.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Data;

import javax.persistence.*;

@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String userName;
    private String password;
    private String avatar;
    private String email;
    private String phone;
    private String createTime;
    private int isAdmin;

    public User() {
    }

    public User(int id, String userName, String password,int isAdmin, String avatar, String email, String phone, String createTime) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.avatar = avatar;
        this.email = email;
        this.phone = phone;
        this.createTime = createTime;
        this.isAdmin = isAdmin;
    }
}
