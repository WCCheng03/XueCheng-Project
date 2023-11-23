package com.xuecheng.checkcode.service.impl;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.xuecheng.checkcode.model.CheckCodeParamsDto;
import com.xuecheng.checkcode.model.CheckCodeResultDto;
import com.xuecheng.checkcode.service.AbstractCheckCodeService;
import com.xuecheng.checkcode.service.CheckCodeService;
import com.xuecheng.checkcode.service.FindPasswordService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @ClassName FindPasswordServiceImpl
 * @Author Chen9
 * @Date 2023/11/23 17:22
 * @VERSION 1.0
 * @Description TODO
 * @Program XueCheng-Project
 **/
@Service
@Slf4j
public class FindPasswordServiceImpl implements FindPasswordService {


//    @Override
//    public CheckCodeResultDto generate(CheckCodeParamsDto checkCodeParamsDto) {
//        GenerateResult generate = generate(checkCodeParamsDto, 6, "checkcode:", 300);
//        String key = generate.getKey();
//        String code = generate.getCode();
//        CheckCodeResultDto checkCodeResultDto = new CheckCodeResultDto();
//        checkCodeResultDto.setAliasing(code);
//        checkCodeResultDto.setKey(key);
//        return checkCodeResultDto;
//    }

    @Override
    public CheckCodeResultDto generateCheckCode(String param1) {
        return null;
    }

//    @Override
//    public CheckCodeResultDto generateCheckCode(String param1) {
//        CheckCodeParamsDto checkCodeParamsDto = new CheckCodeParamsDto();
//        AbstractCheckCodeService.GenerateResult generate = abstractCheckCodeService.generate(checkCodeParamsDto, 6, ":", 300);
//
//        CheckCodeResultDto checkCodeResultDto = new CheckCodeResultDto();
//        checkCodeResultDto.setKey(generate.getKey());
//        checkCodeResultDto.setAliasing(null);
//        return checkCodeResultDto;
//    }

}
