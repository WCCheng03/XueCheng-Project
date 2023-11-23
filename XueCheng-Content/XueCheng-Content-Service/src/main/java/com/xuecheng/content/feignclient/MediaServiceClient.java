package com.xuecheng.content.feignclient;

import com.xuecheng.content.config.MultipartSupportConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @autor Chen9
 * @date 2023/11/22 09:00
 * @description 远程调用媒资的接口
 */
@FeignClient(value = "media-api",configuration = {MultipartSupportConfig.class},fallbackFactory = MediaServiceClientFallbackFactory.class)
public interface MediaServiceClient {
    @PostMapping(value = "/media/upload/coursefile", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseBody
    public String upload(@RequestPart("filedata") MultipartFile filedata,
                                      @RequestParam(value = "objectName", required = false) String objectName) throws IOException;
}
