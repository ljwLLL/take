package com.hpower.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @Classname 统计图对象
 * @Description TODO
 * @Date 2020/4/3 9:59 上午
 * @Created yangyang.jiang
 */
@Data
@ApiModel(value = "统计图对象",description = "统计图对象")
public class DataYearCommonVo extends BaseVo{

    @ApiModelProperty(value = "标签名称")
    private String tagName;

    @ApiModelProperty(value = "明细对象集合")
    private List<DataYearCommonDetailVo> list;
}
