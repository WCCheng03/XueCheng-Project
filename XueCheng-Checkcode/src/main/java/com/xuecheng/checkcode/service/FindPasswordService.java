package com.xuecheng.checkcode.service;

import com.xuecheng.checkcode.model.CheckCodeResultDto;

public interface FindPasswordService {
    CheckCodeResultDto generateCheckCode(String param1);
}
