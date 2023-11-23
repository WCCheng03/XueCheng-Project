package com.xuecheng.ucenter.service;

import com.xuecheng.ucenter.model.po.XcUser;

/**
 * @autor Chen9
 * @date 2023/11/23 13:24
 * @description 微信认证接口
 */
public interface WxAuthService {
    /**
     * @author CHEN9
     * @date 2023/11/23 13:27
     * @description 微信扫码登录，申请令牌，携带令牌获取用户信息并保存到数据库
     **/
    XcUser wxAuth(String code);
}
