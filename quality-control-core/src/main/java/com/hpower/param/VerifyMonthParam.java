package com.hpower.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @Classname 审核月度报表入参对象
 * @Description TODO
 * @Date 2020/3/30 6:28 下午
 * @Created yangyang.jiang
 */
@Data
@ApiModel(value = "审核月度报表入参对象",description = "审核月度报表入参对象")
public class VerifyMonthParam extends BaseParam{

    @ApiModelProperty(value = "月度报表id")
    @NotNull(message = "月度报表id不能为空")
    private Long monthId;

    @ApiModelProperty(value = "审核状态 0 驳回 1 通过")
    @NotNull(message = "审核状态不能为空")
    private Integer state;

    @ApiModelProperty(value = "审核意见")
    private String remark;
}
