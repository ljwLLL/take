package com.hpower.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @Classname 获取当前用户对象返回
 * @Description TODO
 * @Date 2020/3/26 4:05 下午
 * @Created yangyang.jiang
 */
@Data
@ApiModel(value = "当前用户对象", description = "当前用户对象")
public class LoginUserVo extends BaseVo {

    @ApiModelProperty(value = "用户id")
    private Long id;

    @ApiModelProperty(value = "用户名称")
    private String name;

    @ApiModelProperty(value = "医院id")
    private Long hospitalId;

    @ApiModelProperty(value = "用户电话号码")
    private String mobile;

    @ApiModelProperty(value = "质控中心id")
    private Long qualityId;

    @ApiModelProperty(value = "年龄")
    private Integer age;

    @ApiModelProperty(value = "性别")
    private String sex;

    @ApiModelProperty(value = "省份id")
    private Long provinceId;

    @ApiModelProperty(value = "省份名称")
    private String provinceName;

    @ApiModelProperty(value = "当前登录人菜单信息")
    private List<UserMenuVo> userMenuVoList;
}
