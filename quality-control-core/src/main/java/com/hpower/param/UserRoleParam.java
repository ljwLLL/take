package com.hpower.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @Classname 用户角色对象
 * @Description TODO
 * @Date 2020/3/29 12:02 上午
 * @Created yangyang.jiang
 */
@Data
@ApiModel(value = "用户角色对象", description = "用户角色对象")
public class UserRoleParam extends BaseParam {

    @ApiModelProperty(value = "用户id")
    @NotNull(message = "用户id不能为空")
    private Long userId;

    @ApiModelProperty(value = "角色id串")
    @NotEmpty(message = "角色id串不能为空")
    private String roleIds;
}
