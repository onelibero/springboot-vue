package com.comic.springbootproject.comment.service.impl;

import com.comic.springbootproject.comment.dao.ComicCommentDao;
import com.comic.springbootproject.comment.dao.CommentReplyContentDao;
import com.comic.springbootproject.comment.entity.ComicComment;
import com.comic.springbootproject.comment.entity.Comment;
import com.comic.springbootproject.comment.entity.CommentReplyContent;
import com.comic.springbootproject.comment.service.CommentReplyContentService;
import com.comic.springbootproject.common.vo.Result;
import com.comic.springbootproject.common.vo.Search;
import com.comic.springbootproject.user.dao.UserDao;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CommentReplyContentServiceImpl implements CommentReplyContentService {
    @Resource
    private CommentReplyContentDao commentReplyContentDao;
    @Resource
    private ComicCommentDao comicCommentDao;
    @Resource
    private UserDao userDao;


    @Override
    @Transactional
    public List<CommentReplyContent> selectCommentReplyContentByComicCommentId(int ComicCommentId) {
        List<CommentReplyContent> commentReplyContents = commentReplyContentDao.
                selectCommentReplyContentByComicCommentId(ComicCommentId);
        SimpleDateFormat sdfDatetime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //循环遍历，进行数据转换
        for (CommentReplyContent commentReplyContent : commentReplyContents) {
            //将用户id转换为用户名
            commentReplyContent.setReplyUserName(userDao.getUserById(commentReplyContent.getReplyId()).getUserName());
            commentReplyContent.setRepliedUserName(userDao.getUserById(commentReplyContent.getRepliedId()).getUserName());
            //头像转换
            commentReplyContent.setReplyAvatar(userDao.getUserById(commentReplyContent.getReplyId()).getAvatar());
            commentReplyContent.setRepliedAvatar(userDao.getUserById(commentReplyContent.getRepliedId()).getAvatar());
            //时间转换
            commentReplyContent.setCreateTime(sdfDatetime.format(new Date(Long.parseLong(commentReplyContent.getCreateTime()))));
            //评论内容转换
            commentReplyContent.setComment(comicCommentDao.selectCommentByCommentId(commentReplyContent.getCommentId()).getComment());
        }
        return commentReplyContents;
    }

    @Override
    @Transactional
    public Result<CommentReplyContent> insertCommentReplyContent(CommentReplyContent commentReplyContent) {
        String time = new Date().getTime() + "";
        commentReplyContent.setCreateTime(time);
        Comment comment = new Comment();
        comment.setComment(commentReplyContent.getComment());
        comicCommentDao.insertComment(comment);
        commentReplyContent.setCommentId(comment.getId());
        commentReplyContentDao.insertCommentReplyContent(commentReplyContent);
        return Result.ok("评论成功，注意评论规范", commentReplyContent);
    }

    @Override
    @Transactional
    public Result<Object> deleteCommentReplyContentByCommentReplyContentId(int commentReplyContentId) {
        CommentReplyContent commentReplyContent = commentReplyContentDao.
                selectCommentReplyContentById(commentReplyContentId);
        if (commentReplyContent.getCommentId() != 0) {
            comicCommentDao.deleteCommentByCommentId(commentReplyContent.getCommentId());
        }
        commentReplyContentDao.deleteCommentReplyContentByCommentReplyContentId
                (commentReplyContentId);
        return Result.ok("删除评论成功");
    }
}

