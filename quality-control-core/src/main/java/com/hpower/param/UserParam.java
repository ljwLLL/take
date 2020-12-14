package com.hpower.param;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "用户入参对象",description = "用户入参对象")
public class UserParam {

    @ApiModelProperty(value = "用户名")
    private String userName;
}
