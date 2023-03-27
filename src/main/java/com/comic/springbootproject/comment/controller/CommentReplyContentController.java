package com.comic.springbootproject.comment.controller;

import com.comic.springbootproject.comment.entity.CommentReplyContent;
import com.comic.springbootproject.comment.service.CommentReplyContentService;
import com.comic.springbootproject.common.vo.Result;
import com.comic.springbootproject.common.vo.Search;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Delete;
import org.springframework.data.repository.query.Param;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/commentReplyContent")
public class CommentReplyContentController {
    @Resource
    private CommentReplyContentService commentReplyContentService;

    /**
     * URL: http://localhost/commentReplyContent/insertCommentReplyContent
     * @param commentReplyContent :{replyId:1,repliedId:1,comment:"hello",comicCommentId:1}
     * @return 插入信息
     */
    @PostMapping(value = "/insertCommentReplyContent", consumes = MediaType.APPLICATION_JSON_VALUE)
    Result<CommentReplyContent> insertCommentReplyContent(@RequestBody CommentReplyContent commentReplyContent) {
        return commentReplyContentService.insertCommentReplyContent(commentReplyContent);
    }

    /**
     * URL: http://localhost/commentReplyContent/deleteCommentReplyContentByCommentReplyContentId/1
     * @param commentReplyContentId 评论回复内容id
     * @return 是否成功
     */
    @DeleteMapping(value = "/deleteCommentReplyContentByCommentReplyContentId/{commentReplyContentId}")
    Result<Object> deleteCommentReplyContentByCommentReplyContentId(@PathVariable("commentReplyContentId") int commentReplyContentId){
        return commentReplyContentService.deleteCommentReplyContentByCommentReplyContentId(commentReplyContentId);
    }

    /**
     * URL: http://localhost/commentReplyContent/selectCommentReplyContentList/{ComicCommentId}
     * @param ComicCommentId
     * @return 评论回复内容列表
     */
    @GetMapping(value = "/selectCommentReplyContentList/{ComicCommentId}")
    List<CommentReplyContent> selectCommentReplyContentByComicCommentId(@PathVariable("ComicCommentId") int ComicCommentId){
        return commentReplyContentService.selectCommentReplyContentByComicCommentId(ComicCommentId);
    }

}
