package com.comic.springbootproject.user.service.Impl;

import ch.qos.logback.core.BasicStatusManager;
import com.comic.springbootproject.comic.entity.Comic;
import com.comic.springbootproject.comic.service.ComicService;
import com.comic.springbootproject.common.vo.Result;
import com.comic.springbootproject.user.dao.UserRecord;
import com.comic.springbootproject.user.entity.UserCollection;
import com.comic.springbootproject.user.entity.UserHistory;
import com.comic.springbootproject.user.service.UserRecordService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UserRecordServiceImpl implements UserRecordService {
    @Resource
    private UserRecord userRecord;
    @Resource
    private ComicService comicService;

    @Override
    @Transactional
    public Result<Object> insertHistory(UserHistory userHistory) {
        userHistory.setCreateTime(new Date().getTime()+"");
        if(userRecord.selectHistory(userHistory) != null){
            userRecord.updateHistory(userHistory);
        }else{
            userRecord.insertHistory(userHistory);
        }
        return Result.ok("添加成功");
    }

    @Override
    @Transactional
    public Result<Object> selectHistoryByUserId(int id) {
        List<Integer> list = userRecord.selectHistoryByUserId(id);
        List<Comic> comicHistory = new ArrayList<>();
        for (Integer integer : list) {
            Comic comic = comicService.getComicById(integer);
            comicHistory.add(comic);
        }
        if(comicHistory.size() == 0){
            return Result.failed("没有历史记录");
        }
        return Result.ok("查找成功",comicHistory);

    }

    @Override
    @Transactional
    public Result<Object> selectCollectionByUserId(int id) {
        List<Integer> list = userRecord.selectCollectionByUserId(id);
         List<Comic> comicCollection = new ArrayList<>();
        for (Integer integer : list) {
            comicCollection.add(comicService.getComicById(integer));
        }
         return Result.ok(comicCollection);
    }

    @Override
    @Transactional
    public Result<UserCollection> insertCollection(UserCollection userCollection) {
        if(userRecord.selectCollection(userCollection) != null){
            return Result.failed("已经收藏过了");
        }else{
            userRecord.insertCollection(userCollection);
            return Result.ok();
        }
    }

    @Override
    @Transactional
    public Result<Object> deleteCollection(UserCollection userCollection) {
        userRecord.deleteCollection(userCollection);
        return Result.ok();
    }

}
