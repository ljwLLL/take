package com.hpower.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @Classname 月度明细信息
 * @Description TODO
 * @Date 2020/4/3 9:08 上午
 * @Created yangyang.jiang
 */
@Data
@ApiModel(value = "月度明细信息", description = "月度明细信息")
public class DataMonthElseVo extends BaseVo {

    @ApiModelProperty(value = "医院名称")
    private String hospitalName;

    @ApiModelProperty(value = "月平均")
    private BigDecimal value;
}
