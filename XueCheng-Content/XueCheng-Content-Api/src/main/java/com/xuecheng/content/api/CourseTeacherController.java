package com.xuecheng.content.api;

import com.xuecheng.base.model.PageResult;
import com.xuecheng.content.model.po.CourseTeacher;
import com.xuecheng.content.service.CourseTeacherService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName CourseTeacherController
 * @Author Chen9
 * @Date 2023/11/15 10:20
 * @VERSION 1.0
 * @Description 师资管理
 * @Program XueCheng-Project
 **/
@Slf4j
@RestController
@RequestMapping("/courseTeacher")
public class CourseTeacherController {
    @Autowired
    CourseTeacherService courseTeacherService;

    @ApiOperation("师资管理的查询")
    @GetMapping("/list/{id}")
    public List<CourseTeacher> selectCourseTeacher(@PathVariable Long id) {

        return courseTeacherService.getCourseTeacher(id);
    }

    @ApiOperation("师资管理的增加和修改")
    @PostMapping
    public List<CourseTeacher> insertOrUpdateCourseTeacher(@RequestBody CourseTeacher courseTeacher){
        //机构id，由于认证系统没有上线暂时硬编码
        Long companyId = 1232141425L;
        return courseTeacherService.changeCourceTeacher(companyId,courseTeacher);
    }

    @ApiOperation("师资管理教师删除")
    @DeleteMapping("/course/{courseId}/{teacherId}")
    public void deleteCourseTeacher(@PathVariable Long courseId,@PathVariable Long teacherId){
        courseTeacherService.deleteCourseTeacher(courseId,teacherId);
    }
}
