package com.hpower.param;

import com.hpower.validator.groups.AddGroup;
import com.hpower.validator.groups.UpdateGroup;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @Classname 新增角色入参对象
 * @Description TODO
 * @Date 2020/3/30 8:25 上午
 * @Created yangyang.jiang
 */
@Data
@ApiModel(value = "新增角色入参对象", description = "新增角色入参对象")
public class RoleAddParam extends BaseParam {

    @ApiModelProperty(value = "角色id")
    @NotNull(message = "角色id不能为空", groups = {UpdateGroup.class})
    private Long id;

    @ApiModelProperty(value = "角色编码")
    private String roleCode;

    @ApiModelProperty(value = "角色名称")
    @NotEmpty(message = "角色名称不能为空")
    private String roleName;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "是否启用")
    @NotNull(message = "是否启用不能为空", groups = {AddGroup.class})
    private Integer enabled;
}
