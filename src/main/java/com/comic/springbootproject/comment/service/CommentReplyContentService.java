package com.comic.springbootproject.comment.service;

import com.comic.springbootproject.comment.entity.ComicComment;
import com.comic.springbootproject.comment.entity.CommentReplyContent;
import com.comic.springbootproject.common.vo.Result;
import com.comic.springbootproject.common.vo.Search;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface CommentReplyContentService {
    List<CommentReplyContent> selectCommentReplyContentByComicCommentId(int ComicCommentId);


    Result<CommentReplyContent> insertCommentReplyContent(CommentReplyContent commentReplyContent);

    Result<Object> deleteCommentReplyContentByCommentReplyContentId(int commentReplyContentId);

}
