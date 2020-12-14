package com.hpower.param;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @Classname 分配用户数据权限对象
 * @Description TODO
 * @Date 2020/3/30  下午
 * @Created jianwen.liu
 */
@Data
@ApiModel(value = "分配用户数据权限对象", description = "分配用户数据权限对象")
public class AuthorizationParam extends BaseParam {


    @ApiModelProperty(value = "用户id")
    @NotNull(message = "用户id不能为空")
    private Long userId;

    @ApiModelProperty(value = "数据权限类型")
    @NotNull(message = "数据权限类型不能为空")
    private Integer type;

    @ApiModelProperty(value = "医院id串")
    private String hospitalIds;
}
