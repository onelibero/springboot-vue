package com.comic.springbootproject.comment.dao;

import com.comic.springbootproject.comic.entity.Comic;
import com.comic.springbootproject.comment.entity.ComicComment;
import com.comic.springbootproject.comment.entity.CommentReplyContent;
import com.comic.springbootproject.common.vo.Search;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface CommentReplyContentDao {

        @Insert("insert into comment_reply_content(commentId,replyId,repliedId," +
                "createTime,comicCommentId) " +
                "values(#{commentId}, #{replyId}, " +
                "#{repliedId}, #{createTime}, #{comicCommentId})")
        void insertCommentReplyContent(CommentReplyContent commentReplyContent);
        @Delete("delete from comment_reply_content where id = #{commentReplyContentId}")
        void deleteCommentReplyContentByCommentReplyContentId(int commentReplyContentId);

        @Select("select * from comment_reply_content where comicCommentId = #{ComicCommentId} order by createTime asc")
        List<CommentReplyContent> selectCommentReplyContentByComicCommentId(int ComicCommentId);

        @Select("select * from comment_reply_content where id = #{CommentReplyContentId}")
        CommentReplyContent selectCommentReplyContentById(int CommentReplyContentId);
}
