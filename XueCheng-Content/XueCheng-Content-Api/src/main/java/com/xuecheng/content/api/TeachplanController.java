package com.xuecheng.content.api;


import com.xuecheng.base.exception.XueChengException;
import com.xuecheng.base.model.PageResult;
import com.xuecheng.content.model.dto.SaveTeachplanDto;
import com.xuecheng.content.model.dto.TeachplanDto;
import com.xuecheng.content.model.po.Teachplan;
import com.xuecheng.content.service.TeachplanService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @description 课程计划编辑接口
 * @author Mr.M
 * @date 2022/9/6 11:29
 * @version 1.0
 */
@Api(value = "课程计划编辑接口",tags = "课程计划编辑接口")
@RestController
public class TeachplanController {
    @Resource
    TeachplanService teachplanService;


    @ApiOperation("查询课程计划树形结构")
    @ApiImplicitParam(value = "courseId",name = "课程Id",required = true,dataType = "Long",paramType = "path")
    @GetMapping("/teachplan/{courseId}/tree-nodes")
    public List<TeachplanDto> getTreeNodes(@PathVariable Long courseId){
        return teachplanService.findTeachplanTree(courseId);
    }

    @ApiOperation("课程计划创建或修改")
    @PostMapping("/teachplan")
    public void saveTeachplan(@RequestBody SaveTeachplanDto teachplan){
        teachplanService.saveTeachplan(teachplan);
    }

    @ApiOperation("删除课程计划")
    @DeleteMapping("/teachplan/{teachplanId}")
    public Boolean deleteTeachplan(@PathVariable Long teachplanId){
        Integer i = teachplanService.deleteTeachplan(teachplanId);
        if (i!=1) XueChengException.cast("课程计划信息还有子级信息，无法操作");
        return true;
    }

    @ApiOperation("课程计划上移")
    @PostMapping("/teachplan/moveup/{teachplanId}")
    public Boolean moveUpTeachplan(@PathVariable Long teachplanId){
        return teachplanService.moveUpTeachplan(teachplanId);
    }
    @ApiOperation("课程计划下移")
    @PostMapping("/teachplan/movedown/{teachplanId}")
    public Boolean moveDownTeachplan(@PathVariable Long teachplanId){
        return teachplanService.moveDownTeachplan(teachplanId);
    }

}

