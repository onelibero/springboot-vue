package com.comic.springbootproject.comic.service.impl;

import com.comic.springbootproject.comic.dao.ComicDao;
import com.comic.springbootproject.comic.entity.Comic;
import com.comic.springbootproject.comic.service.ComicService;
import com.comic.springbootproject.common.vo.Result;
import com.comic.springbootproject.common.vo.Search;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ComicServiceImpl implements ComicService {

    @Resource
    private ComicDao comicDao;

    //实现动漫添加
    @Override
    @Transactional
    public Result<Comic> insertComic(Comic comic) {
        Comic temp  = comicDao.getComicByName(comic.getComicName());
        if( temp!=null){
            return Result.failed("名字重复");
        }

        //插入新动漫
        comicDao.insertComic(comic);

        // 返回结果
        return Result.ok(comic);
    }

    //实现动漫更新
    @Override
    @Transactional
    public Result<Comic> updateComic(Comic comic) {

        Comic temp = comicDao.getComicByName(comic.getComicName());
        if(temp!=null && temp.getId() != comic.getId()){
            return Result.failed("动漫名称重复");
        }
        comicDao.updateComic(comic);

        return Result.ok(comic);
    }

    //实现动漫删除
    @Override
    @Transactional
    public Result<Object> deleteComicById(int id) {
        //调用dao层删除方法
        comicDao.deleteComicById(id);
        return Result.ok();
    }

    //根据id查
    @Override
    @Transactional
    public Comic getComicById(int id) {
        return comicDao.getComicById(id);
    }

    //分页查询
    @Override
    @Transactional
    public PageInfo<Comic> getComicListBySearch(Search search) {
        // 初始化search对象
        search.initSearch();
        // 开启分页
        PageHelper.startPage(search.getCurrentPage(), search.getPageSize());
        // 返回 pageinfo 对象
//		return new PageInfo<>(userDao.getUsersBySearch(search));
        return new PageInfo<>(Optional
                .ofNullable(comicDao.getComicBySearch(search))
                .orElse(Collections.emptyList()));
    }

    @Override
    @Transactional
    public PageInfo<Comic> getComicListByPopularity() {
        Search search = new Search();
        search.initSearch();
        PageHelper.startPage(search.getCurrentPage(), search.getPageSize());
        return new PageInfo<>(Optional
                .ofNullable(comicDao.getComicBySearch(search))
                .orElse(Collections.emptyList()));
    }
}
