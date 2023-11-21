package com.xuecheng.content.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @ClassName FreemarkerController
 * @Author Chen9
 * @Date 2023/11/21 9:09
 * @VERSION 1.0
 * @Description Freemarker入门
 * @Program XueCheng-Project
 **/
@Controller
public class FreemarkerController {
    @GetMapping("/testfreemarker")
    public ModelAndView test(){
        ModelAndView modelAndView = new ModelAndView();
        //设置模型数据
        modelAndView.addObject("name","小明");
        //设置模板名称
        modelAndView.setViewName("test");
        return modelAndView;
    }

}
