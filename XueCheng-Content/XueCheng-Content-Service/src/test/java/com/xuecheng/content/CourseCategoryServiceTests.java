package com.xuecheng.content;

import com.xuecheng.content.mapper.CourseCategoryMapper;
import com.xuecheng.content.model.dto.CourseCategoryTreeDto;
import com.xuecheng.content.service.CourseCategoryService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @ClassName CourseCategoryServiceTests
 * @Author Chen9
 * @Date 2023/11/13 14:40
 * @VERSION 1.0
 * @Program XueCheng-Project
 **/
@SpringBootTest
@Slf4j
public class CourseCategoryServiceTests {
    @Autowired
    CourseCategoryMapper courseCategoryMapper;
    @Autowired
    CourseCategoryService courseCategoryService;
    @Test
    void testqueryTreeNodes() {
        List<CourseCategoryTreeDto> courseCategoryTreeDtos = courseCategoryService.queryTreeNodes("1");
        System.out.println(courseCategoryTreeDtos);
    }

}
