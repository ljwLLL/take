package com.hpower.vo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Classname 用户权限返回对象
 * @Description TODO
 * @Date 2020/3/24 9:01 上午
 * @Created yangyang.jiang
 */
@Data
@ApiModel(value = "用户权限返回对象", description = "用户权限返回对象")
public class AuthorizationVo extends BaseVo {

    @ApiModelProperty(value = "数据权限类型")
    private Integer type;

    @ApiModelProperty(value = "医院id串")
    private String hospitalIds;
}
