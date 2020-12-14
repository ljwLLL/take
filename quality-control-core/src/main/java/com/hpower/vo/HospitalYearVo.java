package com.hpower.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Classname 年度报表返回对象
 * @Description TODO
 * @Date 2020/3/31 3:03 下午
 * @Created yangyang.jiang
 */
@Data
@ExcelTarget(value = "年度报表")
@ApiModel(value = "年度报表返回对象", description = "年度报表返回对象")
public class HospitalYearVo extends BaseVo {

    @ApiModelProperty(value = "年度报表id")
    private Long yearId;

    @Excel(name = "医院名称",width = 40)
    @ApiModelProperty(value = "医院名称")
    private String hospitalName;

    @Excel(name = "年份",width = 10)
    @ApiModelProperty(value = "年份")
    private String year;

    @Excel(name = "上报时间", width = 20)
    @ApiModelProperty(value = "上报时间")
    private String time;

    @Excel(name = "上报人名称", width = 30)
    @ApiModelProperty(value = "上报人")
    private String name;

    @Excel(name = "手机号", width = 20)
    @ApiModelProperty(value = "手机号码")
    private String mobile;

    @Excel(name = "所属科室", width = 20)
    @ApiModelProperty(value = "科室")
    private String dept;

    @Excel(name = "职称", width = 20)
    @ApiModelProperty(value = "职称")
    private String post;

    @ApiModelProperty(value = "状态")
    private Integer state;

    @ApiModelProperty(value = "上报状态", hidden = true)
    @Excel(name = "上报状态", width = 20)
    private String stateName;

    @ApiModelProperty(value = "医院等级")
    private Integer level;

}
