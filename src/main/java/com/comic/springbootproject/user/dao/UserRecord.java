package com.comic.springbootproject.user.dao;

import com.comic.springbootproject.user.entity.UserCollection;
import com.comic.springbootproject.user.entity.UserHistory;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
@Mapper
public interface UserRecord {
    //插入浏览历史，如果已经存在则不做更改
    @Insert("insert into user_history(user_id, comic_id, create_time) " +
            "values(#{userId}, #{comicId}, #{createTime})")
    void insertHistory(UserHistory userHistory);
    @Select("select * from user_history where user_id = #{userId} and comic_id = #{comicId}")
    UserHistory selectHistory(UserHistory userHistory);

    @Update("update user_history set create_time = #{createTime} where user_id = #{userId} and comic_id = #{comicId}")
    void updateHistory(UserHistory userHistory);
    @Select("select comic_id from user_history where user_id = #{id}")
    List<Integer> selectHistoryByUserId(int id);
    @Insert("insert into user_collection(user_id, comic_id) " +
            "values(#{userId}, #{comicId})")
    void insertCollection(UserCollection userCollection);
    @Delete("delete from user_collection where user_id = #{userId} and comic_id = #{comicId}")
    void deleteCollection(UserCollection userCollection);
    @Update("update user_collection set comic_id = #{comicId} where user_id = #{userId} and comic_id = #{comicId}")
    void updateCollection(UserCollection userCollection);
    @Select("select * from user_collection where user_id = #{userId} and comic_id = #{comicId}")
    UserCollection selectCollection(UserCollection userCollection);
    @Select("select comic_id from user_collection where user_id = #{id}")
    List<Integer> selectCollectionByUserId(int id);
}
