package com.xuecheng.content.model.dto;

import lombok.Data;
import lombok.ToString;

/**
 * @ClassName QueryCourseParamsDto
 * @Author 24348
 * @Date 2023/11/9 19:56
 * @VERSION 1.0
 * @Description 课程查询参数Dto
 * @Program XueCheng-Project
 **/
@Data
@ToString
public class QueryCourseParamsDto {
    //审核状态
    private String auditStatus;
    //课程名称
    private String courseName;
    //发布状态
    private String publishStatus;

}
