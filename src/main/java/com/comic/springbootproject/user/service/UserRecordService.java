package com.comic.springbootproject.user.service;

import com.comic.springbootproject.common.vo.Result;
import com.comic.springbootproject.user.entity.UserCollection;
import com.comic.springbootproject.user.entity.UserHistory;

import java.util.List;

public interface UserRecordService {
    Result<Object> insertHistory(UserHistory userHistory);
    Result<Object> selectHistoryByUserId(int id);
    Result<Object> selectCollectionByUserId(int id);
    Result<UserCollection> insertCollection(UserCollection userCollection);

    Result<Object> deleteCollection(UserCollection userCollection);
    //Result<UserCollection> updateCollection(UserCollection userCollection);

}
