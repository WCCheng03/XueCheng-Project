package com.xuecheng.content;

import com.xuecheng.content.mapper.TeachplanMapper;
import com.xuecheng.content.model.dto.TeachplanDto;
import com.xuecheng.content.service.TeachplanService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class TeachplanMapperTests {
    @Autowired
    TeachplanMapper teachplanMapper;
    @Autowired
    TeachplanService teachplanService;
    @Test
    public void testSelectTreeNodes(){
        List<TeachplanDto> teachplanDtos = teachplanMapper.selectTreeNodes(117L);
        System.out.println(teachplanDtos);
    }

    @Test
    public void testdeleteTeachplan(){
        Integer i = teachplanService.deleteTeachplan(263L);
        System.out.println(i==1);
    }

    @Test
    public void testMoveUpTeachplan(){
        teachplanService.moveUpTeachplan(271L);
    }
}
