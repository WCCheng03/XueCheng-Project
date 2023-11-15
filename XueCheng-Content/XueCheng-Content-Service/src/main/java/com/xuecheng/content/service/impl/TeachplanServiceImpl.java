package com.xuecheng.content.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.xuecheng.base.exception.XueChengException;
import com.xuecheng.content.mapper.TeachplanMapper;
import com.xuecheng.content.mapper.TeachplanMediaMapper;
import com.xuecheng.content.model.dto.SaveTeachplanDto;
import com.xuecheng.content.model.dto.TeachplanDto;
import com.xuecheng.content.model.po.Teachplan;
import com.xuecheng.content.model.po.TeachplanMedia;
import com.xuecheng.content.service.TeachplanService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Mr.M
 * @version 1.0
 * @description 课程计划service接口实现类
 * @date 2022/9/9 11:14
 */
@Service
public class TeachplanServiceImpl implements TeachplanService {

    @Autowired
    TeachplanMapper teachplanMapper;
    @Autowired
    TeachplanMediaMapper teachplanMediaMapper;

    @Override
    public List<TeachplanDto> findTeachplanTree(long courseId) {
        return teachplanMapper.selectTreeNodes(courseId);
    }

    @Transactional
    @Override
    public void saveTeachplan(SaveTeachplanDto teachplanDto) {

        //课程计划id
        Long id = teachplanDto.getId();
        //修改课程计划
        if (id != null) {
            Teachplan teachplan = teachplanMapper.selectById(id);
            BeanUtils.copyProperties(teachplanDto, teachplan);
            teachplanMapper.updateById(teachplan);
        } else {
            //取出同父同级别的课程计划数量
            int count = getTeachplanCount(teachplanDto.getCourseId(), teachplanDto.getParentid());
            Teachplan teachplanNew = new Teachplan();
            //设置排序号
            teachplanNew.setOrderby(count + 1);
            BeanUtils.copyProperties(teachplanDto, teachplanNew);

            teachplanMapper.insert(teachplanNew);
        }
    }

    @Override
    /**
     * @Author CHEN9
     * @Date 2023/11/15 08:54
     * @Description 删除课程计划
     * @param id
     * @return java.lang.Boolean
     **/
    public Integer deleteTeachplan(Long id) {
        Teachplan teachplan = teachplanMapper.selectById(id);
        if (teachplan != null) {
            if (teachplan.getParentid() != 0) {
                int teachplanResult = teachplanMapper.deleteById(id);
                LambdaQueryWrapper<TeachplanMedia> queryWrapper = new LambdaQueryWrapper<>();
                queryWrapper.eq(TeachplanMedia::getTeachplanId, id);
                TeachplanMedia teachplanMedia = teachplanMediaMapper.selectOne(queryWrapper);
                if (teachplanMedia != null) {
                    int teachplanMediaResult = teachplanMediaMapper.deleteById(teachplanMedia.getId());
                    return teachplanMediaResult;
                }
                return teachplanResult;
            }
            if (teachplan.getParentid() == 0) {
                Long tid = teachplan.getId();
                LambdaQueryWrapper<Teachplan> LambdaQueryWrapper = new LambdaQueryWrapper<>();
                LambdaQueryWrapper.eq(Teachplan::getParentid, tid);
                Teachplan teachplan1 = teachplanMapper.selectOne(LambdaQueryWrapper);
                if (teachplan1 != null) return 0;
                return teachplanMapper.deleteById(tid);
            }
        }
        return 0;
    }

    @Override
    /**
     * @Author CHEN9
     * @Date 2023/11/15 09:47
     * @Description 上移课程计划
     * @param teachplanId
     * @return java.lang.Boolean
     **/
    public Boolean moveUpTeachplan(Long teachplanId) {
        Teachplan teachplan = teachplanMapper.selectById(teachplanId);
        Integer orderby = teachplan.getOrderby();
        if (orderby == 1) XueChengException.cast("课程计划已经是第一位");

        Integer moveTo = orderby - 1;
        teachplan.setOrderby(moveTo);
        Long parentid = teachplan.getParentid();

        LambdaQueryWrapper<Teachplan> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Teachplan::getParentid, parentid);
        lambdaQueryWrapper.eq(Teachplan::getOrderby, moveTo);
        Teachplan teachplanMoveTo = teachplanMapper.selectOne(lambdaQueryWrapper);
        teachplanMoveTo.setOrderby(orderby);

        teachplanMapper.updateById(teachplan);
        teachplanMapper.updateById(teachplanMoveTo);
        return true;
    }

    @Override
    /**
     * @Author CHEN9
     * @Date 2023/11/15 09:47
     * @Description 下移课程计划
     * @param teachplanId
     * @return java.lang.Boolean
     **/
    public Boolean moveDownTeachplan(Long teachplanId) {
        Teachplan teachplan = teachplanMapper.selectById(teachplanId);
        Integer orderby = teachplan.getOrderby();
        int teachplanCount = getTeachplanCount(teachplan.getCourseId(), teachplan.getParentid());
        if (orderby == teachplanCount) XueChengException.cast("课程计划已经是最后一位");

        Integer moveTo = orderby + 1;
        teachplan.setOrderby(moveTo);
        Long parentid = teachplan.getParentid();

        LambdaQueryWrapper<Teachplan> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Teachplan::getParentid, parentid);
        lambdaQueryWrapper.eq(Teachplan::getOrderby, moveTo);
        Teachplan teachplanMoveTo = teachplanMapper.selectOne(lambdaQueryWrapper);
        teachplanMoveTo.setOrderby(orderby);

        teachplanMapper.updateById(teachplan);
        teachplanMapper.updateById(teachplanMoveTo);
        return true;
    }

    /**
     * @param courseId 课程id
     * @param parentId 父课程计划id
     * @return int 最新排序号
     * @description 获取最新的排序号
     * @author Mr.M
     * @date 2022/9/9 13:43
     */
    private int getTeachplanCount(long courseId, long parentId) {
        LambdaQueryWrapper<Teachplan> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Teachplan::getCourseId, courseId);
        queryWrapper.eq(Teachplan::getParentid, parentId);
        Integer count = teachplanMapper.selectCount(queryWrapper);
        return count;
    }

}
