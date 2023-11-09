package com.xuecheng.base.model;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.java.Log;

/**
 * @Author CHEN9
 * @Date 2023/11/9 19:57
 * @Description 分页查询通用参数
 * @Param
 * @return
 **/
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PageParams {

    //当前页码
    @ApiModelProperty("当前页码")
    private Long pageNo = 1L;

    //每页记录数默认值
    @ApiModelProperty("每页记录数")
    private Long pageSize =5L;

    public PageParams(long pageNo,long pageSize){
        this.pageNo = pageNo;
        this.pageSize = pageSize;
    }
}