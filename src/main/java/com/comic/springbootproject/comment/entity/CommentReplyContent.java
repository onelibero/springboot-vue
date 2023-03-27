package com.comic.springbootproject.comment.entity;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

@Data
public class CommentReplyContent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int commentId;
    private int replyId;
    private int repliedId;
    private String createTime;
    private int comicCommentId;
    @Transient
    private String comment;
    @Transient
    private String replyUserName;
    @Transient
    private String replyAvatar;
    @Transient
    private String repliedUserName;
    @Transient
    private String repliedAvatar;

    public CommentReplyContent(int id, int commentId, int replyId, int repliedId, String createTime, int comicCommentId) {
        this.id = id;
        this.commentId = commentId;
        this.replyId = replyId;
        this.repliedId = repliedId;
        this.createTime = createTime;
        this.comicCommentId = comicCommentId;
    }

    public CommentReplyContent() {
    }
}
