package com.comic.springbootproject.user.controller;

import com.comic.springbootproject.common.vo.Result;
import com.comic.springbootproject.common.vo.Search;
import com.comic.springbootproject.user.entity.User;
import com.comic.springbootproject.user.entity.UserCollection;
import com.comic.springbootproject.user.entity.UserHistory;
import com.comic.springbootproject.user.service.UserRecordService;
import com.comic.springbootproject.user.service.UserService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/user")
@Api(tags = "用户管理")
public class UserController {
    @Resource
    private UserService userService;
    @Resource
    private UserRecordService userRecordService;

    /**
     *URL: <a href="http://localhost/user/insertUser">...</a> ----post
     * @param {"userName":"lhj","password":"111111","email":"17990531116@qq.com","phone":17990531116}
     * @return result信息
     */
    @PostMapping(value = "/insertUser",consumes = MediaType.APPLICATION_JSON_VALUE)

    public Result<User> insertUser(@RequestBody User user) {
        return userService.insertUser(user);
    }

    /**
     * URL: <a href="http://localhost/user/getUserById/{id}">...</a> ----get
     * @param id
     * @return user对象
     */
    @GetMapping(value = "/getUserById/{id}")
    public User getUserById(@PathVariable int id) {
        return userService.getUserById(id);
    }

    /**
     * URL: <a href="http://localhost/user/updateUser">...</a> ----put
     * @param user
     * @return
     */
    @PutMapping(value = "/updateUser",consumes = MediaType.APPLICATION_JSON_VALUE)
    public Result<User> updateUser(@RequestBody User user) {
        return userService.updateUser(user);
    }

    /**
     * URL: <a href="http://localhost/user/deleteUser/{id}">...</a> ----delete
     * @param id
     * @return
     */
    @DeleteMapping(value = "/deleteUser/{id}")
    public Result<Object> deleteUser(@PathVariable("id") int id) {
        return userService.deleteUser(id);
    }

    /**
     * URL: <a href="http://localhost/user/login">...</a> ----post
     * @param user：{"userName":"chenchenchen","password":"000812"}
     * @return 成功的话返回user对象，失败的话返回错误信息
     */
    @PostMapping(value = "/login",consumes = MediaType.APPLICATION_JSON_VALUE)
    public Result<User> login(@RequestBody User user) {
        return userService.login(user);
    }

    /**
     * URL: <a href="http://localhost/user/setAdmin/{id}">...</a> ----put
     * @param id:用户id
     * @return 是否成功
     */
    @PutMapping(value = "/setAdmin/{id}")
    public Result<User> setAdmin(@PathVariable("id") int id) {
        return userService.setAdmin(id);
    }

    /**
     * URL: <a href="http://localhost/user/userList">...</a> ----post
     * @param search ：{"pageNum":1,"pageSize":5,"orderBy":"id desc","searchValue":"chen"}
     * @return 返回分页信息
     */
    @PostMapping(value = "/userList",consumes = MediaType.APPLICATION_JSON_VALUE)
    public PageInfo<User> getUserBySearch (@RequestBody Search search) {
        return userService.getUserBySearch(search);
    }

    /**
     * URL: <a href="http://localhost/user/insertHistory">...</a> ----post
     * @param userHistory:{"userId":1,"comicId":1}
     * @return 是否成功
     */
    @PostMapping(value = "/insertHistory",consumes = MediaType.APPLICATION_JSON_VALUE)
    public Result<Object> insertHistory(@RequestBody UserHistory userHistory) {
        return userRecordService.insertHistory(userHistory);
    }

    /**
     * URL: <a href="http://localhost/user/selectHistoryByUserId/{id}">...</a> ----get
     * @param id:用户id
     * @return 返回用户浏览记录
     */
    @GetMapping(value = "/selectHistoryByUserId/{id}")
    public Result<Object> selectHistoryByUserId(@PathVariable("id") int id) {
        return userRecordService.selectHistoryByUserId(id);
    }

    /**
     * URL: <a href="http://localhost/user/selectCollectionByUserId/{id}">...</a> ----get
     * @param id
     * @return
     */
    @GetMapping(value = "/selectCollectionByUserId/{id}")
    public Result<Object> selectCollectionByUserId(@PathVariable("id") int id) {
        return userRecordService.selectCollectionByUserId(id);
    }

    /**
     * URL: <a href="http://localhost/user/deleteCollection">...</a> ----delete
     * @param userCollection:{"userId":1,"comicId":1}
     * @return 是否成功
     */
    @DeleteMapping(value = "/deleteCollection",consumes = MediaType.APPLICATION_JSON_VALUE)
    public Result<Object> deleteCollection(@RequestBody UserCollection userCollection) {
        return userRecordService.deleteCollection(userCollection);
    }

    /**
     * URL: <a href="http://localhost/user/insertCollection">...</a> ----post
     * @param userCollection:{"userId":1,"comicId":1}
     * @return 是否成功
     */
    @PostMapping(value = "/insertCollection",consumes = MediaType.APPLICATION_JSON_VALUE)
    public Result<UserCollection> insertCollection(@RequestBody UserCollection userCollection) {
        return userRecordService.insertCollection(userCollection);
    }
}
