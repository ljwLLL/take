package com.hpower.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

/**
 * @Classname RoleOneVo
 * @Description TODO
 * @Date 2020/3/30 8:36 上午
 * @Created yangyang.jiang
 */
@Data
@Builder
@ApiModel(value = "根据id获取角色对象", description = "根据id获取角色对象")
public class RoleOneVo extends BaseVo {

    @ApiModelProperty(value = "角色id")
    private Long id;

    @ApiModelProperty(value = "角色编码")
    private String roleCode;

    @ApiModelProperty(value = "角色名称")
    private String roleName;

    @ApiModelProperty(value = "描述")
    private String remark;
}
