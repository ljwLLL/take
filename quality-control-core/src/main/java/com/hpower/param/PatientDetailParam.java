package com.hpower.param;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @Classname 根据城市查询具体医院月平均明细信息
 * @Description TODO
 * @Date 2020/4/3 9:10 上午
 * @Created yangyang.jiang
 */
@Data
@ApiModel(value = "根据城市查询具体医院月平均明细信息", description = "根据城市查询具体医院月平均明细信息")
public class PatientDetailParam extends BasePageParam {

    @ApiModelProperty(value = "入参类型0近一个月 1近半年 2近一年 3时间筛查 4无条件")
    @NotNull(message = "查询类型不能为空")
    private Integer type;

    @ApiModelProperty(value = "开始月份")
    private String startMonth;

    @ApiModelProperty(value = "结束月份")
    private String endMonth;

    @ApiModelProperty(value = "查询指标")
    @NotEmpty(message = "查询指标不能为空")
    private String tag;

    @ApiModelProperty(value = "城市id")
    private Long cityId;
}
