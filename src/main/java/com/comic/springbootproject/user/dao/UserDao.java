package com.comic.springbootproject.user.dao;

import com.comic.springbootproject.common.vo.Search;
import com.comic.springbootproject.user.entity.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface UserDao {
    @Insert("insert into user_table (userName,password,avatar,email,phone,createTime) " +
            "values (#{userName}, #{password},#{avatar},#{email},#{phone},#{createTime})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    void insertUser(User user);

    @Select("select * from user_table where userName = #{userName}")
    User getUserByName(String userName);
    @Update("update user_table set userName = #{userName}, password = #{password}," +
            "avatar=#{avatar}, email=#{email}, phone=#{phone}, createTime = #{createTime} where id = #{id}")
    void updateUser(User user);

    @Update("update user_table set userName = #{userName}," +
            "avatar=#{avatar}, email=#{email}, phone=#{phone}, createTime = #{createTime} where id = #{id}")
    void updateUserExpPassword(User user);

    @Delete("delete from user_table where id = #{id}")
    void deleteUser(int id);
    @Select("select * from user_table where id = #{id}")
    User getUserById(int id);
    @Select("select * from user_table where userName=#{userName} and password=#{password} limit 1")
    User login(@Param("userName") String userName,@Param("password") String password);
    @Update("update user_table set isAdmin = 1 where id = #{id}")
    void setAdmin(int id);
    @Select("<script>"
            + "select * from user_table"
            +"<where>"
            +"<if test= 'keyword != \"\" and keyword != null ' >"
            + " and (userName like '%${keyword}%' or email like '%${keyword}%' or " +
            "phone like '%${keyword}%')"
            +"</if>"
            +"</where>"
            +"<choose>"
            +"<when test='sort != \"\" and sort != null '>"
            +" order by ${sort} ${direction}"
            +"</when>"
            +"<otherwise>"
            +" order by id asc"
            +"</otherwise>"
            +"</choose>"
            +"</script>")
    List<User> getUserBySearch(Search search);
}


