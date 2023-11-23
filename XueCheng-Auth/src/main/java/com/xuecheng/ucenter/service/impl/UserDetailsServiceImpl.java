package com.xuecheng.ucenter.service.impl;

import com.alibaba.fastjson.JSON;
import com.xuecheng.ucenter.mapper.XcMenuMapper;
import com.xuecheng.ucenter.mapper.XcUserMapper;
import com.xuecheng.ucenter.model.dto.AuthParamsDto;
import com.xuecheng.ucenter.model.dto.XcUserExt;
import com.xuecheng.ucenter.model.po.XcMenu;
import com.xuecheng.ucenter.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName UserDetailsServiceImpl
 * @Author Chen9
 * @Date 2023/11/23 9:29
 * @VERSION 1.0
 * @Description 用户登录授权
 * @Program XueCheng-Project
 **/
@Component
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    XcUserMapper xcUserMapper;

    @Autowired
    ApplicationContext applicationContext;

    @Autowired
    XcMenuMapper xcMenuMapper;

    //传入的请求认证的参数就是AuthParamsDto
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        //将传入的json转成AuthParamDto对象
        AuthParamsDto authParamsDto = null;
        try {
            authParamsDto = JSON.parseObject(s, AuthParamsDto.class);
        } catch (Exception e) {
            throw new RuntimeException("请求认证参数不符合要求");
        }

        //获取登录的类型
        String authType = authParamsDto.getAuthType();
        //根据不同登录类型从spring容器取出指定的bean
        String beanName = authType + "_authservice";
        AuthService bean = applicationContext.getBean(beanName, AuthService.class);
        //调用统一方法execute完成认证
        XcUserExt xcUserExt = bean.execute(authParamsDto);
        //封装xcUserExt用户信息位UserDetails
        UserDetails userPrincipal = getUserPrincipal(xcUserExt);

        return userPrincipal;
    }

    /**
     * @param user 用户id，主键
     * @return com.xuecheng.ucenter.model.po.XcUser 用户信息
     * @description 查询用户信息
     * @author Mr.M
     * @date 2022/9/29 12:19
     */
    public UserDetails getUserPrincipal(XcUserExt xcUser) {
        //用户权限,如果不加报Cannot pass a null GrantedAuthority collection
        String password = xcUser.getPassword();
        String[] authorities = {};
        //根据用户id查询用户权限
        List<XcMenu> xcMenus = xcMenuMapper.selectPermissionByUserId(xcUser.getId());
        if (xcMenus.size() > 0) {
            List<String> permissions = new ArrayList<>();
            xcMenus.forEach(m -> {
                //拿到权限标识符
                permissions.add(m.getCode());
            });
            //将permissions转成数组
            authorities = permissions.toArray(new String[0]);
        }
        //为了安全在令牌中不放密码
        xcUser.setPassword(null);
        //将user对象转json
        String userString = JSON.toJSONString(xcUser);
        //创建UserDetails对象
        UserDetails userDetails = User.withUsername(userString).password(password).authorities(authorities).build();
        return userDetails;
    }

}
