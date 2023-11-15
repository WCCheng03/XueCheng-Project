package com.xuecheng.content.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.xuecheng.base.exception.XueChengException;
import com.xuecheng.content.mapper.CourseBaseMapper;
import com.xuecheng.content.mapper.CourseTeacherMapper;
import com.xuecheng.content.model.po.CourseBase;
import com.xuecheng.content.model.po.CourseTeacher;
import com.xuecheng.content.service.CourseTeacherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * @ClassName CourseTeacherServiceImpl
 * @Author Chen9
 * @Date 2023/11/15 10:24
 * @VERSION 1.0
 * @Description 师资管理接口实现
 * @Program XueCheng-Project
 **/
@Slf4j
@Service
public class CourseTeacherServiceImpl implements CourseTeacherService {
    @Autowired
    CourseTeacherMapper courseTeacherMapper;

    @Autowired
    CourseBaseMapper courseBaseMapper;


    @Override
    /**
     * @author CHEN9
     * @date 2023/11/15 10:59
     * @description 查询授课师资信息
     * @param [id]
     * @return java.util.List<com.xuecheng.content.model.po.CourseTeacher>
     **/
    public List<CourseTeacher> getCourseTeacher(Long id) {
        LambdaQueryWrapper<CourseTeacher> teacherLambdaQueryWrapper = new LambdaQueryWrapper<>();
        teacherLambdaQueryWrapper.eq(CourseTeacher::getCourseId,id);
        return courseTeacherMapper.selectList(teacherLambdaQueryWrapper);
    }


    /**
     * @author CHEN9
     * @date 2023/11/15 10:59
     * @description 修改或添加授课师资信息
     * @param companyId, id
     * @return com.xuecheng.content.model.po.CourseTeacher
     **/
    @Override
    @Transactional
    public List<CourseTeacher> changeCourceTeacher(Long companyId, CourseTeacher courseTeacher) {
        CourseBase courseBase = courseBaseMapper.selectById(courseTeacher.getCourseId());
        if(courseBase==null){
            XueChengException.cast("课程不存在");
        }
        if (!courseBase.getCompanyId().equals(companyId)){
            XueChengException.cast("只允许向机构自己的课程中添加老师、删除老师。");
        }
        Long id = courseTeacher.getId();
        //id为空说明是添加教师
        if (id == null){
            courseTeacher.setCreateDate(LocalDateTime.now());
            courseTeacherMapper.insert(courseTeacher);
            return this.getCourseTeacher(courseTeacher.getCourseId());
        }
        courseTeacherMapper.updateById(courseTeacher);
        return this.getCourseTeacher(courseTeacher.getCourseId());
    }

    /**
     * @author CHEN9
     * @date 2023/11/15 11:26
     * @description 删除授课老师
     * @param courseId,teacherId
     **/
    @Override
    public void deleteCourseTeacher(Long courseId, Long teacherId) {
        LambdaQueryWrapper<CourseTeacher> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(CourseTeacher::getCourseId,courseId);
        lambdaQueryWrapper.eq(CourseTeacher::getId,teacherId);
        courseTeacherMapper.delete(lambdaQueryWrapper);
    }
}
