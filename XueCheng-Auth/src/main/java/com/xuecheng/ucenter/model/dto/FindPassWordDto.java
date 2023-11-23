package com.xuecheng.ucenter.model.dto;

import com.xuecheng.ucenter.model.po.XcUser;
import lombok.Data;

/**
 * @ClassName FindPassWordDto
 * @Author Chen9
 * @Date 2023/11/23 18:04
 * @VERSION 1.0
 * @Description 找回密码模型类
 * @Program XueCheng-Project
 **/
@Data
public class FindPassWordDto extends XcUser {
    private String checkCodeKey;
    private String checkCode;
    private String confirmpwd;
    private String passWord;
}
