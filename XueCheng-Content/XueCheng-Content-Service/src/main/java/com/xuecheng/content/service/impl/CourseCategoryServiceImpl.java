package com.xuecheng.content.service.impl;

import com.xuecheng.content.mapper.CourseBaseMapper;
import com.xuecheng.content.mapper.CourseCategoryMapper;
import com.xuecheng.content.model.dto.AddCourseDto;
import com.xuecheng.content.model.dto.CourseBaseInfoDto;
import com.xuecheng.content.model.dto.CourseCategoryTreeDto;
import com.xuecheng.content.model.po.CourseBase;
import com.xuecheng.content.service.CourseCategoryService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @ClassName CourseCategoryServiceImpl
 * @Author Chen9
 * @Date 2023/11/13 14:45
 * @VERSION 1.0
 * @Program XueCheng-Project
 **/
@Service
@Slf4j
public class CourseCategoryServiceImpl implements CourseCategoryService {
    @Autowired
    CourseCategoryMapper courseCategoryMapper;

    @Override
    public List<CourseCategoryTreeDto> getQueryTreeNodes(String id) {
        List<CourseCategoryTreeDto> courseCategoryTreeDtos = courseCategoryMapper.selectTreeNodes(id);
        //将list转map,以备使用,排除根节点
        Map<String, CourseCategoryTreeDto> mapTemp = courseCategoryTreeDtos
                .stream()
                .filter(item -> !id.equals(item.getId()))
                .collect(Collectors.toMap(key -> key.getId(), value -> value, (key1, key2) -> key2));

        //最终返回的list
        ArrayList<CourseCategoryTreeDto> categoryTreeDtos = new ArrayList<>();
        //依次遍历每个元素,排除根节点
        courseCategoryTreeDtos
                .stream()
                .filter(item -> !id.equals(item.getId()))
                .forEach(item -> {
                    //向list写入元素
                    if (item.getParentid().equals(id)) {
                        categoryTreeDtos.add(item);
                    }
                    //到每个子节点放到父节点的childrenTreeNodes属性中
                    //找到当前节点的父节点
                    CourseCategoryTreeDto courseCategoryTreeDto = mapTemp.get(item.getParentid());
                    if (courseCategoryTreeDto != null) {
                        if (courseCategoryTreeDto.getChildrenTreeNodes() == null) {
                            courseCategoryTreeDto.setChildrenTreeNodes(new ArrayList<CourseCategoryTreeDto>());
                        }
                        courseCategoryTreeDto.getChildrenTreeNodes().add(item);
                    }
                });
        return categoryTreeDtos;
    }
}
