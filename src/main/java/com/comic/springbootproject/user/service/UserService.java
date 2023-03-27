package com.comic.springbootproject.user.service;

import com.comic.springbootproject.common.vo.Result;
import com.comic.springbootproject.common.vo.Search;
import com.comic.springbootproject.user.entity.User;
import com.github.pagehelper.PageInfo;

public interface UserService {
    Result<User> insertUser(User user);

    Result<User> updateUser(User user);

    Result<Object> deleteUser(int id);

    User getUserById(int id);

    Result<User> login(User user);

    Result<User> setAdmin(int id);

    PageInfo<User> getUserBySearch(Search search);


}
