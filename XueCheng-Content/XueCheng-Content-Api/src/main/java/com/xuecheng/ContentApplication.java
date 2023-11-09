package com.xuecheng;

import com.spring4all.swagger.EnableSwagger2Doc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @ClassName ContentApplication
 * @Author 24348
 * @Date 2023/11/9 20:06
 * @VERSION 1.0
 * @Description 内容管理服务启动类
 * @Program XueCheng-Project
 **/
@SpringBootApplication
@EnableSwagger2Doc  //开启生成swagger接口文档
public class ContentApplication {
    public static void main(String[] args) {
        SpringApplication.run(ContentApplication.class, args);
    }
}
