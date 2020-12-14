package com.hpower.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Classname 年份报表单条记录信息
 * @Description TODO
 * @Date 2020/4/1 3:44 下午
 * @Created yangyang.jiang
 */
@Data
@ApiModel(value = "年份报表单条记录信息", description = "年份报表单条记录信息")
public class HospitalYearOneVo extends BaseVo {

    @ApiModelProperty(value = "年份报表id")
    private Long yearId;

    @ApiModelProperty(value = "基础信息")
    private YearBaseVo baseVo;

    @ApiModelProperty(value = "功能信息")
    private YearFunctionVo functionVo;

    @ApiModelProperty(value = "用户信息")
    private YearUserVo userVo;

    @ApiModelProperty(value = "填写人")
    private String name;

    @ApiModelProperty(value = "部门")
    private String dept;

    @ApiModelProperty(value = "职称")
    private String post;

    @ApiModelProperty(value = "手机号码")
    private String mobile;

    @ApiModelProperty(value = "备注")
    private String remark;
}
