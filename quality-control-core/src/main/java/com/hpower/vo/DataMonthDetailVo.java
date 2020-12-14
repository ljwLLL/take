package com.hpower.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @Classname 单个指标明细信息
 * @Description TODO
 * @Date 2020/4/2 3:45 下午
 * @Created yangyang.jiang
 */
@Data
@ApiModel(value = "单个指标明细信息",description = "单个指标明细信息")
public class DataMonthDetailVo extends BaseVo{

    @ApiModelProperty(value = "城市名称")
    private String cityName;

    @ApiModelProperty(value = "月平均")
    private BigDecimal value;

    @ApiModelProperty(value = "城市id")
    private Long cityId;
}
