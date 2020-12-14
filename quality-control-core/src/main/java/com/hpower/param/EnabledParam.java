package com.hpower.param;

import com.hpower.enums.WhetherEnum;
import com.hpower.validator.constraints.Enum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * 启用停用请求参数对象
 *
 * @author yangyang.jiang
 * @date 2020/03/28
 * @since 1.0
 */
@EqualsAndHashCode(callSuper = false)
@Data
@Builder
@ApiModel("启用停用请求参数对象")
public class EnabledParam extends BaseParam {

    @NotNull(message = "主键不能为空")
    @ApiModelProperty(value = "主键", required = true, example = "1", notes = "根据实际接口传递不同对象的主键")
    private Long id;

    /**
     * 启用状态:0 、停用，1、启用
     */
    @ApiModelProperty(value = "启用状态:0 、停用，1、启用", required = true, example = "1", position = Integer.MAX_VALUE)
    @NotNull(message = "启用状态只能为0或1")
    @Enum(message = "启用状态只能为0或1", target = {WhetherEnum.class})
    private Integer enabled;
}
