package com.comic.springbootproject.user.service.Impl;

import com.comic.springbootproject.common.vo.Result;
import com.comic.springbootproject.common.vo.Search;
import com.comic.springbootproject.user.dao.UserDao;
import com.comic.springbootproject.user.entity.User;
import com.comic.springbootproject.user.service.UserService;
import com.comic.springbootproject.util.MD5Util;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.beans.Transient;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;
    SimpleDateFormat sdfDatetime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    @Override
    @Transactional
    public Result<User> insertUser(User user) {
        //  根据用户名字查找用户是否存在
        User temp = userDao.getUserByName(user.getUserName());
        //  如果存在返回错误信息
        if (temp != null) {
            return Result.failed("用户名已存在");
        }
        String time = new Date().getTime() + "";
        //初始化user属性
        user.setCreateTime(time);
        user.setPassword(MD5Util.getMD5(user.getPassword()));
        userDao.insertUser(user);
        user.setCreateTime(sdfDatetime.format(Long.parseLong(user.getCreateTime())));
        return Result.ok(user);
    }

    @Override
    @Transactional
    public Result<User> updateUser(User user) {
        User temp = userDao.getUserByName(user.getUserName());
        User temp2 = userDao.getUserById(user.getId());
        //  如果存在返回错误信息
        if (temp != null && temp.getId() != user.getId()) {
            return Result.failed("用户名已存在");
        }
        String time = new Date().getTime() + "";
        //初始化user属性
        user.setCreateTime(time);
        if (Objects.equals(user.getPassword(),temp2.getPassword())) {
            userDao.updateUserExpPassword(user);
        }else {
            user.setPassword(MD5Util.getMD5(user.getPassword()));
            userDao.updateUser(user);
        }
        user.setCreateTime(sdfDatetime.format(Long.parseLong(user.getCreateTime())));
        return Result.ok(user);
    }

    @Override
    @Transactional
    public Result<Object> deleteUser(int id) {
        userDao.deleteUser(id);
        return Result.ok();
    }

    @Override
    @Transactional
    public User getUserById(int id) {
        SimpleDateFormat sdfDatetime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        User user = userDao.getUserById(id);
        Long time = Long.parseLong(user.getCreateTime());
        user.setCreateTime(sdfDatetime.format(time));
        return user;
    }

    @Override
    @Transactional
    public Result<User> login(User user) {
        User temp = userDao.login(user.getUserName(), MD5Util.getMD5(user.getPassword()));
        if (temp != null) {
            temp.setCreateTime(sdfDatetime.format(Long.parseLong(temp.getCreateTime())));
            return Result.ok(temp);
        }
        return Result.failed("用户名或密码错误");
    }

    @Override
    @Transactional
    public Result<User> setAdmin(int id) {
        User temp = userDao.getUserById(id);
        if (temp.getIsAdmin() == 1) {
            return Result.failed("该用户已经是管理员");
        }
        userDao.setAdmin(id);
        temp.setIsAdmin(1);
        return Result.ok(temp);
    }

    @Override
    @Transactional
    public PageInfo<User> getUserBySearch(Search search) {

        search.initSearch();
        PageHelper.startPage(search.getCurrentPage(), search.getPageSize());
        return new PageInfo<>(Optional.ofNullable
                (userDao.getUserBySearch(search)).orElse(Collections.emptyList()));
    }
}
