package com.hpower.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @Classname 总览年度报表查询对象
 * @Description TODO
 * @Date 2020/4/3 10:15 上午
 * @Created yangyang.jiang
 */
@Data
@ApiModel(value = "总览年度报表查询对象", description = "总览年度报表查询对象")
public class DataYearParam extends BaseParam {

    @ApiModelProperty(value = "查询类型 0近一年 1条件 2无条件")
    @NotNull(message = "查询类型不能为空")
    private Integer type;

    @ApiModelProperty(value = "开始年份")
    private String startYear;

    @ApiModelProperty(value = "结束年份")
    private String endYear;
}
