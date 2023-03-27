package com.comic.springbootproject.common.controller;


import com.comic.springbootproject.common.service.ImageService;
import com.comic.springbootproject.common.vo.Result;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.FileNotFoundException;

@RestController
@RequestMapping(value = "/api/common")
public class ImageController {
    @Resource
    private ImageService imageService;

    /**
     * 127.0.0.1/api/common/image/PROFILE  --- post
     *  return image url
     */
    @PostMapping(value = "/image/{imageTypeName}",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Result<String> uploadImage(@RequestParam MultipartFile file,
                                      @PathVariable String imageTypeName) throws FileNotFoundException {
        return imageService.uploadImage(file, imageTypeName);
    }
}
