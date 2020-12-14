package com.hpower.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Classname 用户角色对象
 * @Description TODO
 * @Date 2020/3/28 9:48 下午
 * @Created yangyang.jiang
 */
@Data
@ApiModel(value = "用户角色对象", description = "用户角色对象")
public class UserRoleVo extends BaseVo {

    @ApiModelProperty(value = "角色id串")
    private String roleIds;
}
