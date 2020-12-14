package com.hpower.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @Classname 角色菜单入参对象
 * @Description TODO
 * @Date 2020/3/30 9:46 上午
 * @Created yangyang.jiang
 */
@Data
@ApiModel(value = "角色菜单入参对象", description = "角色菜单入参对象")
public class RoleMenuParam extends BaseParam {

    @ApiModelProperty(value = "角色id")
    @NotNull(message = "角色id不能为空")
    private Long roleId;

    @ApiModelProperty(value = "菜单id串不能为空")
    @NotEmpty(message = "菜单id串不能为空")
    private String menuIds;
}
