package com.hpower.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Classname 角色返回参数类
 * @Description TODO
 * @Date 2020/3/29 12:15 上午
 * @Created yangyang.jiang
 */
@Data
@ApiModel(value = "角色返回参数类", description = "角色返回参数类")
public class RoleVo extends BaseVo {

    @ApiModelProperty(value = "角色id")
    private Long id;

    @ApiModelProperty(value = "角色名称")
    private String roleName;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "秒速")
    private String remark;

    @ApiModelProperty(value = "启用状态")
    private Integer enabled;
}
