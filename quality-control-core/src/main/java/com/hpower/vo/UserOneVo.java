package com.hpower.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Classname 根据单个用户对象
 * @Description TODO
 * @Date 2020/3/28 9:48 下午
 * @Created yangyang.jiang
 */
@Data
@ApiModel(value = "根据单个用户对象", description = "根据单个用户对象")
public class UserOneVo extends BaseVo {

    @ApiModelProperty(value = "用户主键id")
    private Long id;

    @ApiModelProperty(value = "医院id")
    private Long hospitalId;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "性别")
    private Integer sex;

    @ApiModelProperty(value = "科室")
    private String department;

    @ApiModelProperty(value = "职务")
    private String post;

    @ApiModelProperty(value = "手机号码")
    private String mobile;
}
