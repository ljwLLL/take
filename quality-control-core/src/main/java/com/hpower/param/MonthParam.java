package com.hpower.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @Classname 新增医院月度报表
 * @Description TODO
 * @Date 2020/3/31 12:54 下午
 * @Created yangyang.jiang
 */
@Data
@ApiModel(value = "新增医院月度报表", description = "新增医院月度报表")
public class MonthParam extends BaseParam {

    @ApiModelProperty(value = "医院id")
    @NotNull(message = "医院id不能为空")
    private Long hospitalId;

    @ApiModelProperty(value = "月份")
    @NotEmpty(message = "月份不能为空")
    private String month;


}
