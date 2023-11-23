package com.xuecheng.content.api;

import com.xuecheng.base.exception.ValidationGroups;
import com.xuecheng.base.model.PageParams;
import com.xuecheng.base.model.PageResult;
import com.xuecheng.content.model.dto.AddCourseDto;
import com.xuecheng.content.model.dto.CourseBaseInfoDto;
import com.xuecheng.content.model.dto.EditCourseDto;
import com.xuecheng.content.model.dto.QueryCourseParamsDto;
import com.xuecheng.content.model.po.CourseBase;
import com.xuecheng.content.service.CourseBaseInfoService;
import com.xuecheng.content.utils.SecurityUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName CourseBaseInfoController
 * @Author Chen9
 * @Date 2023/11/9 20:02
 * @VERSION 1.0
 * @Description 课程信息编辑接口
 * @Program XueCheng-Project
 **/
@Api(value = "课程信息编辑接口",tags = "课程信息编辑接口")
@RestController
public class CourseBaseInfoController {
    @Autowired
    CourseBaseInfoService courseBaseInfoService;
    @ApiOperation("课程分页查询接口")
    @PostMapping("/course/list")
    @PreAuthorize("hasAuthority('xc_teachmanager_course_list')")//指定权限标识符，拥有此权限才能访问此方法
    public PageResult<CourseBase> list(PageParams pageParams,@RequestBody(required = false) QueryCourseParamsDto queryCourseParamsDto){
        //取出用户身份
        SecurityUtil.XcUser user = SecurityUtil.getUser();
        //机构id
        String companyId = user.getCompanyId();

        return courseBaseInfoService.queryCourseBaseList(pageParams, queryCourseParamsDto, Long.parseLong(companyId));
    }

    @ApiOperation("新增课程基础信息")
    @PostMapping("/course")
    public CourseBaseInfoDto createCourseBase(@RequestBody @Validated(ValidationGroups.Inster.class) AddCourseDto addCourseDto) {
        //获取用户所属机构id
        Long companyId = 1232141425L;
        return courseBaseInfoService.createCourseBase(companyId, addCourseDto);
    }

    @ApiOperation("根据课程id查询")
    @GetMapping("/course/{courseId}")
    public CourseBaseInfoDto getCourseBaseById(@PathVariable Long courseId){
        //从jwt的负载中获取当前用户的身份
        SecurityUtil.XcUser user = SecurityUtil.getUser();
        System.out.println(user);
        return courseBaseInfoService.getCourseBaseInfo(courseId);
    }

    @ApiOperation("修改课程基础信息")
    @PutMapping("/course")
    public CourseBaseInfoDto modifyCourseBase(@RequestBody @Validated(ValidationGroups.Update.class) EditCourseDto editCourseDto){
        //机构id，由于认证系统没有上线暂时硬编码
        Long companyId = 1232141425L;
        return courseBaseInfoService.updateCourseBase(companyId,editCourseDto);
    }

    @ApiOperation("删除课程")
    @DeleteMapping("/course/{id}")
    public void deleteCourse(@PathVariable Long id){
        courseBaseInfoService.deleteCourse(id);
    }
}
