package com.hpower.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Classname 返回单个对象信息
 * @Description TODO
 * @Date 2020/3/31 9:45 上午
 * @Created yangyang.jiang
 */
@Data
@ApiModel(value = "返回单个对象信息", description = "返回单个对象信息")
public class HospitalMonthOneVo extends BaseVo {

    @ApiModelProperty(value = "月度报表id")
    private Long monthId;

    @ApiModelProperty(value = "结构指标")
    private StructureVo structureVo;

    @ApiModelProperty(value = "结果指标")
    private ResultVo resultVo;

    @ApiModelProperty(value = "过程指标")
    private ProcessVo processVo;

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
