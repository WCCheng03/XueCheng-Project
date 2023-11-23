package com.xuecheng.ucenter.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @ClassName CheckCodeClient
 * @Author Chen9
 * @Date 2023/11/23 11:17
 * @VERSION 1.0
 * @Description 远程调用校验验证码
 * @Program XueCheng-Project
 **/
@FeignClient(value = "checkcode",fallbackFactory = CheckCodeClientFactory.class)
@RequestMapping("/checkcode")
public interface CheckCodeClient {

    @PostMapping(value = "/verify")
    public Boolean verify(@RequestParam("key") String key, @RequestParam("code") String code);

}

