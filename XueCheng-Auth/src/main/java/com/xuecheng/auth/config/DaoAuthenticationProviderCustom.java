package com.xuecheng.auth.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

/**
 * @ClassName DaoAuthenticationProvider
 * @Author Chen9
 * @Date 2023/11/23 10:05
 * @VERSION 1.0
 * @Description 自定义DaoAuthenticationProvider
 * @Program XueCheng-Project
 **/
@Slf4j
@Component
public class DaoAuthenticationProviderCustom extends DaoAuthenticationProvider {


    @Autowired
    public void setUserDetailsService(UserDetailsService userDetailsService) {
        super.setUserDetailsService(userDetailsService);
    }


    //屏蔽密码对比
    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {


    }

}

