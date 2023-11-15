package com.xuecheng.content.service;

import com.xuecheng.content.model.po.CourseTeacher;

import java.util.List;

public interface CourseTeacherService {

    List<CourseTeacher> getCourseTeacher(Long id);

    List<CourseTeacher> changeCourceTeacher(Long companyId,CourseTeacher courseTeacher);

    void deleteCourseTeacher(Long courseId,Long teacherId);
}
