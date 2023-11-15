package com.xuecheng.content;

import com.xuecheng.content.model.po.CourseTeacher;
import com.xuecheng.content.service.CourseTeacherService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @ClassName CourseTeacherTest
 * @Author Chen9
 * @Date 2023/11/15 10:33
 * @VERSION 1.0
 * @Description 师资管理
 * @Program XueCheng-Project
 **/
@SpringBootTest
@Slf4j
public class CourseTeacherTest {
    @Autowired
    CourseTeacherService courseTeacherService;
    @Test
    public void selectCourseTeacherTest(){
        List<CourseTeacher> courseTeacher = courseTeacherService.getCourseTeacher(22L);
        log.error(courseTeacher.toString());
    }

    @Test
    public void chagneCourseTeacher(){
        CourseTeacher courseTeacher = new CourseTeacher();
        courseTeacher.setCourseId(117L);
//        courseTeacher.setId(22L);
        courseTeacher.setIntroduction("auwahwu");
        courseTeacher.setPosition("naogfiwabnfoai");
        courseTeacher.setTeacherName("haoesigheo");
        courseTeacherService.changeCourceTeacher(1232141425L,courseTeacher);
    }
}
