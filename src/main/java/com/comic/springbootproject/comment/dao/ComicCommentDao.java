package com.comic.springbootproject.comment.dao;


import com.comic.springbootproject.comment.entity.ComicComment;
import com.comic.springbootproject.comment.entity.Comment;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ComicCommentDao {
    @Insert("insert into comic_comment(userId,comicId,commentId,createTime) " +
            "values(#{userId}, #{comicId}, #{commentId}, #{createTime})")
    void insertComicComment(ComicComment comicComment);

    @Delete("delete from comic_comment where id = #{ComicCommentId}")
    void deleteComicCommentByComicCommentId(int ComicCommentId);

    @Insert("insert into comment(comment) " + "values(#{comment})")
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "id", before = false, resultType = int.class)
    void insertComment(Comment comment);
    @Select("select * from comic_comment where id = #{ComicCommentId}")
    ComicComment selectComicCommentByComicCommentId(int ComicCommentId);
    @Select("select * from comment where id = #{commentId}")
    Comment selectCommentByCommentId(int commentId);

    @Delete("delete from comment where id = #{commentId}")
    void deleteCommentByCommentId(int commentId);
    //根据动漫id根据createTime排序
    @Select("select * from comic_comment where comicId = #{comicId} order by createTime asc")
    List<ComicComment> selectCommentByComicId(int comicId);
}
