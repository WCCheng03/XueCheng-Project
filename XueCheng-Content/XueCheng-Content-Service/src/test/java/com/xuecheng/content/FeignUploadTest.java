package com.xuecheng.content;

import com.xuecheng.content.config.MultipartSupportConfig;
import com.xuecheng.content.feignclient.MediaServiceClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @ClassName FeignUploadTest
 * @Author Chen9
 * @Date 2023/11/22 9:03
 * @VERSION 1.0
 * @Description 远程调用媒资服务测试
 * @Program XueCheng-Project
 **/
@SpringBootTest
public class FeignUploadTest {
    @Autowired
    MediaServiceClient mediaServiceClient;
    @Test
    public void test() throws IOException {
        //将file转成Multipart类型
        MultipartFile multipartFile = MultipartSupportConfig.getMultipartFile(new File("D:\\develop\\test.html"));
//        mediaServiceClient.upload(multipartFile,"course","test.html");

    }
}
