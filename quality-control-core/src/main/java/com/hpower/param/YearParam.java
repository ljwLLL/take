package com.hpower.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @Classname 新增年度报表
 * @Description TODO
 * @Date 2020/4/3 2:58 下午
 * @Created yangyang.jiang
 */
@Data
@ApiModel(value = "新增年度报表对象",description = "新增年度报表对象")
public class YearParam extends BaseParam{

    @ApiModelProperty(value = "医院id")
    @NotNull(message = "医院id不能为空")
    private Long hospitalId;

    @ApiModelProperty(value = "年份")
    @NotEmpty(message = "年份不能为空")
    private String year;
}
