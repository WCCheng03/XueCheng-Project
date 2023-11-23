package com.xuecheng.content.feignclient;

import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @ClassName SearchServiceClientFallbackFactory
 * @Author Chen9
 * @Date 2023/11/22 14:06
 * @VERSION 1.0
 * @Description 熔断降级处理
 * @Program XueCheng-Project
 **/
@Slf4j
@Component
public class SearchServiceClientFallbackFactory implements FallbackFactory<SearchServiceClient> {
    @Override
    public SearchServiceClient create(Throwable throwable) {

        return new SearchServiceClient() {

            @Override
            public Boolean add(CourseIndex courseIndex) {
                throwable.printStackTrace();
                log.debug("调用搜索发生熔断走降级方法,熔断异常:", throwable.getMessage());

                return false;
            }
        };
    }
}

