package com.hfut.beike.controller;

import com.hfut.beike.common.R;
import com.hfut.beike.config.MinioConfig;
import com.hfut.beike.utils.MinioUtil;
import io.minio.messages.Bucket;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @Classname UploadController
 * @Description
 * @Date 2023/1/28 17:22
 * @Created by shukz
 */
@Slf4j
@RestController
@RequestMapping(value = "product/file")
public class UploadController extends ApiController {
    @Autowired
    private MinioUtil minioUtil;
    @Autowired
    private MinioConfig prop;

    @GetMapping("/bucketExists")
    public R<?> bucketExists(@RequestParam("bucketName") String bucketName) {
        return success(minioUtil.bucketExists(bucketName));
    }

    @GetMapping("/makeBucket")
    public R<?> makeBucket(String bucketName) {
        return success(minioUtil.makeBucket(bucketName));
    }

    @GetMapping("/removeBucket")
    public R<?> removeBucket(String bucketName) {
        return success(minioUtil.removeBucket(bucketName));
    }

    @GetMapping("/getAllBuckets")
    public R<?> getAllBuckets() {
        List<Bucket> allBuckets = minioUtil.getAllBuckets();
        return success(allBuckets);
    }

    @PostMapping("/upload")
    public R<?> upload(@RequestParam("file") MultipartFile file) {
        String objectName = minioUtil.upload(file);
        if (null != objectName) {
            return success((prop.getEndpoint() + "/" + prop.getBucketName() + "/" + objectName));
        }
        return failed("failed");
    }

    @GetMapping("/preview")
    public R<?> preview(@RequestParam("fileName") String fileName) {
        return success(minioUtil.preview(fileName));
    }

    @GetMapping("/download")
    public R<?> download(@RequestParam("fileName") String fileName, HttpServletResponse res) {
        minioUtil.download(fileName, res);
        return success("ok");
    }

    @PostMapping("/delete")
    public R<?> remove(String url) {
        String objName = url.substring(url.lastIndexOf(prop.getBucketName() + "/") + prop.getBucketName().length() + 1);
        minioUtil.remove(objName);
        return success(objName);
    }
}
