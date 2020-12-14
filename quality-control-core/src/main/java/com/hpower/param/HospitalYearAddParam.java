package com.hpower.param;

import com.hpower.validator.groups.UpdateGroup;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @Classname 年度报表新增对象
 * @Description TODO
 * @Date 2020/4/1 8:20 上午
 * @Created yangyang.jiang
 */
@Data
@ApiModel(value = "年度报表新增对象1",description = "年度报表新增对象1")
public class HospitalYearAddParam extends BaseParam{

    @ApiModelProperty(value = "年份id")
    @NotNull(message = "年份id不能为空",groups = {UpdateGroup.class})
    private Long yearId;

    @ApiModelProperty(value = "年度报表基础区")
    private HospitalYearBaseParam baseParam;

    @ApiModelProperty(value = "年度报表功能区")
    private HospitalYearFunctionParam functionParam;

    @ApiModelProperty(value = "年度报表人员组成区")
    private HospitalYearUserParam userParam;

    @ApiModelProperty(value = "填写人")
    @NotEmpty(message = "填写人不能为空")
    private String name;

    @ApiModelProperty(value = "部门")
    @NotEmpty(message = "部门不能为空")
    private String dept;

    @ApiModelProperty(value = "职称")
    @NotEmpty(message = "职称不能为空")
    private String post;

    @ApiModelProperty(value = "手机号码")
    @NotEmpty(message = "手机号码不能为空")
    private String mobile;

    @ApiModelProperty(value = "备注")
    @NotEmpty(message = "备注不能为空")
    private String remark;
}
