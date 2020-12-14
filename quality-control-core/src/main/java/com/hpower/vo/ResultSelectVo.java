package com.hpower.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 下拉返回对象
 *
 * @author yangyang.jiang
 * @create 2020/03/27
 **/
@Data
@ApiModel(value = "下拉框返回结果对象",description = "下拉框返回结果对象")
public class ResultSelectVo {

    @ApiModelProperty(value = "id标识")
    private String id;

    @ApiModelProperty(value = "名称")
    private String name;
}
