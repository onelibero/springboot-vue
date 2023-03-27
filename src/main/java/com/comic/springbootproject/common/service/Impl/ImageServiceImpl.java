package com.comic.springbootproject.common.service.Impl;

import com.comic.springbootproject.common.service.ImageService;
import com.comic.springbootproject.common.vo.ImageType;
import com.comic.springbootproject.common.vo.Result;
import com.comic.springbootproject.config.ResourceBean;
import com.comic.springbootproject.util.FileUtil;

import org.springframework.boot.system.ApplicationHome;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;

@Service
public class ImageServiceImpl implements ImageService {
    @Resource
    private ResourceBean resourceBean;

    @Override
    public Result<String> uploadImage(MultipartFile mf, String imageTypeName) throws FileNotFoundException {
        if (mf.isEmpty()) {
            return Result.failed("图片为空");
        }

        //  判断图片是否合法
        if (!FileUtil.isImage(mf)) {
            return Result.failed("请上传图片。");
        }

        //获取图片类型
        ImageType it = ImageType.getImageTypeByName(imageTypeName);
        if (it == null) {
            return Result.failed("图片类型不存在");
        }

        //获取文件夹
        String path1 = ResourceUtils.getURL("classpath:").getPath()+"static/"+it.name;
        String realPath = path1.replace('/', '\\').substring(1,path1.length());
        ApplicationHome applicationHome = new ApplicationHome(this.getClass());
        String pre = applicationHome.getDir().getParentFile().getParentFile().
                getAbsolutePath() + "\\src\\main\\resources\\static\\"+it.name;

        //用于查看路径是否正确
        File pathFile1 = new File(realPath);
        if (!pathFile1.exists()) {
            pathFile1.mkdirs();
        }
        File pathFile2 = new File(pre);
        if (!pathFile2.exists()) {
            pathFile2.mkdirs();
        }
        //获取文件名
       // String fileName = String.format("%s.%s", System.currentTimeMillis(),
        //       FileUtil.getFileType(mf.getOriginalFilename()));
        //
        String fileName = new Date().getTime()+"_"+mf.getOriginalFilename();
        //文件写入路径
        String transferPath = realPath+"/"+fileName;
        String transferPath2 = pre+"/"+fileName;


        //获取文件访问路径
        String relatedPath = String.format("/%s/%s", it.name, fileName);

        //写文件
        try {
            mf.transferTo(new File(transferPath));
            mf.transferTo(new File(transferPath2));
        } catch (IOException e) {
            System.out.println("文件写入失败");
                throw new RuntimeException(e);
        }
        return Result.ok(relatedPath);
    }
}
