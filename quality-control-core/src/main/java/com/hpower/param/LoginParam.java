package com.hpower.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @Classname 用户登录入参对象
 * @Description TODO
 * @Date 2020/3/24 8:55 上午
 * @Created by jyy
 */

@Data
@ApiModel(value = "用户登录入参对象", description = "用户登录入参对象")
public class LoginParam extends BaseParam{


    @ApiModelProperty(value = "用户名", required = true, example = "13728999991")
    @NotEmpty(message = "用户名不能为空")
    private String mobile;


    @ApiModelProperty(value = "密码", required = true, example = "123456")
    @NotEmpty(message = "密码不能为空")
    private String password;
}
