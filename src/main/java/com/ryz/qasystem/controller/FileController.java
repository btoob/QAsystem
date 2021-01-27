package com.ryz.qasystem.controller;

import com.ryz.qasystem.Utils.FastDFSUtil;
import com.ryz.qasystem.model.RespBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/file")
public class FileController {

    @Value("${fastdfs.nginx.host}")
    String nginxHost;

    @PostMapping("/upload")
    public RespBean upload(MultipartFile file){
        String fileId = FastDFSUtil.upload(file);
        String url = nginxHost+fileId;
        System.out.println(url);
        return RespBean.ok("图像上传成功", url);
    }
}
