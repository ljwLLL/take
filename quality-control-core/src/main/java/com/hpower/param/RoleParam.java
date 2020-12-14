package com.hpower.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Classname 角色入参对象
 * @Description TODO
 * @Date 2020/3/29 12:14 上午
 * @Created yangyang.jiang
 */
@Data
@ApiModel(value = "角色入参对象", description = "角色入参对象")
public class RoleParam extends BasePageParam {

    @ApiModelProperty(value = "角色名称")
    private String roleName;
}
