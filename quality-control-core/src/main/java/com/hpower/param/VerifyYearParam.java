package com.hpower.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @Classname 审核医院年度报表对象
 * @Description TODO
 * @Date 2020/4/1 4:19 下午
 * @Created yangyang.jiang
 */
@Data
@ApiModel(value = "审核医院年度报表对象",description = "审核医院年度报表对象")
public class VerifyYearParam extends BaseParam{

    @ApiModelProperty(value = "年度报表id")
    @NotNull(message = "年度报表id不能为空")
    private Long yearId;

    @ApiModelProperty(value = "审核状态 0 驳回 1 通过")
    @NotNull(message = "审核状态不能为空")
    private Integer state;

    @ApiModelProperty(value = "审核意见")
    private String remark;
}
