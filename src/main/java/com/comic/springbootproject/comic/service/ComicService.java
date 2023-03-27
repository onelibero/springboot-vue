package com.comic.springbootproject.comic.service;


import com.comic.springbootproject.comic.entity.Comic;
import com.comic.springbootproject.common.vo.Result;
import com.comic.springbootproject.common.vo.Search;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface ComicService {

    Result<Comic> insertComic(Comic comic);

    Result<Comic> updateComic(Comic comic);

    Result<Object> deleteComicById(int id);

    Comic getComicById(int id);

    PageInfo<Comic> getComicListBySearch(Search search);

    PageInfo<Comic> getComicListByPopularity();
}
