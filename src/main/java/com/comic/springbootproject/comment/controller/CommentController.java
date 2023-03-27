package com.comic.springbootproject.comment.controller;


import com.comic.springbootproject.comment.entity.ComicComment;
import com.comic.springbootproject.comment.service.ComicCommentService;
import com.comic.springbootproject.common.vo.Result;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {
    @Resource
    private ComicCommentService comicCommentService;

    /**
     * URL: http://localhost/comment/insertComicComment
     * @param comicComment:{comicId:1,userId:1,comment:"hello"}
     * @return 信息
     */
    @PostMapping(value = "/insertComicComment", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Result<ComicComment> insertComicComment(@RequestBody ComicComment comicComment) {
        return comicCommentService.insertComicComment(comicComment);
    }

    /**
     * URL: http://localhost/comment/deleteComicCommentByComicCommentId/1
     * @param comicCommentId
     * @return 信息
     */
    @DeleteMapping(value = "/deleteComicCommentByComicCommentId/{comicCommentId}")
    public Result<Object> deleteComicCommentByComicCommentId(@PathVariable("comicCommentId") int comicCommentId) {
        return comicCommentService.deleteComicCommentByComicCommentId(comicCommentId);
    }

    /**
     * URL: http://localhost/comment/selectCommentByComicId/1
     * @param comicId
     * @return 评论
     */
    @GetMapping(value = "/selectCommentByComicId/{comicId}")
    public Result<List<ComicComment>> selectCommentByComicId(@PathVariable("comicId") int comicId) {
        return comicCommentService.selectCommentByComicId(comicId);
    }
}
