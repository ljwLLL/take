package com.hpower.param;

import com.hpower.validator.groups.PageGroup;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 分页入参基础类
 *
 * @author yangyang.jiang
 * @date 2020/03/20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "分页入参基础类", description = "调用分页接口公共参数")
public class BasePageParam extends BaseParam {

    @ApiModelProperty(value = "当前页", required = true, example = "1")
    @NotNull(message = "页码不能为空", groups = {PageGroup.class})
    @Range(min = 1, message = "页码必须大于等于1")
    private Integer current;

    @ApiModelProperty(value = "每页显示条数", required = true, example = "10")
    @NotNull(message = "每页显示条数不能为空", groups = {PageGroup.class})
    @Range(min = 1, max = 1000, message = "每页显示条数范围需在1-1000之间")
    private Integer size;

    @ApiModelProperty(value = "启用状态", notes = "0 、停用，1、启用", example = "1")
    @Range(min = 0, max = 1, message = "启用状态只能为0或1")
    private Integer enabled;
}
