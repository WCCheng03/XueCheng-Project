package com.xuecheng.content.feignclient;

import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @ClassName MediaServiceClientFallbackFactory
 * @Author Chen9
 * @Date 2023/11/22 9:31
 * @VERSION 1.0
 * @Description Feign熔断降级处理
 * @Program XueCheng-Project
 **/
@Component
@Slf4j
public class MediaServiceClientFallbackFactory implements FallbackFactory<MediaServiceClient> {
    @Override
    public MediaServiceClient create(Throwable throwable) {
        log.error("throwable:{}",throwable.toString());
        return new MediaServiceClient() {
            @Override
            public String upload(MultipartFile filedata, String objectName) throws IOException {
                return null;
            }
        };
    }
}
