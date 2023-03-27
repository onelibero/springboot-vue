package com.comic.springbootproject.comic.controller;

import com.comic.springbootproject.comic.entity.Comic;
import com.comic.springbootproject.comic.service.ComicService;
import com.comic.springbootproject.common.vo.Result;
import com.comic.springbootproject.common.vo.Search;
import com.github.pagehelper.PageInfo;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;

@RestController
@RequestMapping("/comic")
public class ComicController {

    @Resource
    private ComicService comicService;

    /**
     * localhost/comic/insert   ---post
     * @param comic:{ "comicName":"火影忍者", "nickname":"hy","description"
     *             :"火影忍者是日本漫画家岸本齐史创作的漫画作品，于1999年开始连载于
     *             周刊少年Jump，2006年开始连载于月刊少年Jump，2014年开始连载于
     *             月刊少年Jump GIGA，2016年开始连载于月刊少年Jump SQ。",
     *             "popularity":100, "cover":
     *             "/photo/4a57bc0c-dc38-4b98-b47b-4cbc4f6bedf73.jpg",
     *             "label":"热血冒险", "region":"日本",
     *             "url":"https://www.dongmanmanhua.cn/manhua/huoyingrenzhe/"
     *             remark:"完结",
     *             year:"2002",
     *             updateTime:"2019-12-12",
     *             number:700}
     * @return 返回的comic
     */
    @PostMapping(value = "/insert", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Result<Comic> insertComic(@RequestBody Comic comic) {
        return comicService.insertComic(comic);
    }

    /**
     *  localhost/comic/update   ----put
     * */
    @PutMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Result<Comic> updateComic(@RequestBody Comic comic){
        return comicService.updateComic(comic);
    }

    /**
     *  localhost/comic/delete/{2}   ----delete
     * */
    @DeleteMapping(value = "/delete/{id}")
    public Result<Object> deleteComic(@PathVariable("id") int id){
        return comicService.deleteComicById(id);
    }

    /**
     *  localhost/comic/getComicBySearch   ----post
     *  {"currentPage":1, "pageSize":5, "sort":"id", "direction":"desc", "keyword":""}    ---全查
     *  keyword  即为模糊查询关键字
     *  {"currentPage":1, "pageSize":5, "sort":"id", "direction":"desc", "keyword":"火"}    ---模糊——火
     *  {"currentPage":1, "pageSize":5, "sort":"popularity", "direction":"desc", "keyword":""}   ---按照热度降序，无模糊
     * */
    @PostMapping(value = "/getComicBySearch",consumes = MediaType.APPLICATION_JSON_VALUE)
    public PageInfo<Comic> getComicBySearch(@RequestBody Search search){
        return comicService.getComicListBySearch(search);
    }

    /**
     * localhost/comic/getComicById/2   ----get
     * @param id
     * @return
     */
    @GetMapping(value = "/getComicById/{id}")
    public Comic getComicById(@PathVariable("id") int id) {
        return comicService.getComicById(id);
    }


    /**
     * localhost/comic/getComicByPopularity   ----get
     * @return 按照热度降序排列前五条数据
     */
    @GetMapping(value = "/getComicByPopularity")
    public PageInfo<Comic> getComicListByPopularity() {
        return comicService.getComicListByPopularity();
    }

}
