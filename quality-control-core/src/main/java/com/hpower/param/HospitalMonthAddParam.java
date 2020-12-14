package com.hpower.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @Classname 医院报表填写入参对象
 * @Description TODO
 * @Date 2020/3/30 5:19 下午
 * @Created yangyang.jiang
 */
@Data
@ApiModel(value = "医院报表填写入参对象", description = "医院报表填写入参对象")
public class HospitalMonthAddParam extends BaseParam {

    @ApiModelProperty(value = "月度报表id")
    @NotNull(message = "月度报表id不能为空")
    private Long monthId;

    @ApiModelProperty(value = "结构指标")
    @Valid
    @NotNull(message = "结构指标不能为空")
    private StructureParam structureParam;

    @ApiModelProperty(value = "结果指标")
    @Valid
    @NotNull(message = "结果指标不能为空")
    private ResultParam resultParam;


    @ApiModelProperty(value = "过程指标")
    @Valid
    @NotNull(message = "过程指标不能为空")
    private ProcessParam processParam;

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
