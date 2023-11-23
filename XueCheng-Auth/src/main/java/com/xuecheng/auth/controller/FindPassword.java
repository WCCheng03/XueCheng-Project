package com.xuecheng.auth.controller;

import com.xuecheng.ucenter.feignclient.CheckCodeClient;
import com.xuecheng.ucenter.model.dto.FindPassWordDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName FindPassword
 * @Author Chen9
 * @Date 2023/11/23 18:20
 * @VERSION 1.0
 * @Description TODO
 * @Program XueCheng-Project
 **/
@Controller
public class FindPassword {
    @Autowired
    CheckCodeClient checkCodeClient;


    @PostMapping("/findpassword")
    public Boolean findPassword(@RequestBody FindPassWordDto findPassWordDto){
        String checkCodeKey = findPassWordDto.getCheckCodeKey();
        String checkCode = findPassWordDto.getCheckCode();
        Boolean verify = checkCodeClient.verify(checkCodeKey, checkCode);
        if (verify!=true){
            return verify;
        }
        return true;
    }
}
