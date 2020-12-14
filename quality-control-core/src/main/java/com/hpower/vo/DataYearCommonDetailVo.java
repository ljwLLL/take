package com.hpower.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Classname 统计明细对象
 * @Description TODO
 * @Date 2020/4/3 10:00 上午
 * @Created yangyang.jiang
 */
@Data
@ApiModel(value = "统计明细对象", description = "统计明细对象")
public class DataYearCommonDetailVo {

    @ApiModelProperty(value = "类型")
    private Integer type;

    private String percentage;

    @ApiModelProperty(value = "数量")
    private Integer count;
}
