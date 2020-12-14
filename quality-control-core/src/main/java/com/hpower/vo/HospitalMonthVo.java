package com.hpower.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelEntity;
import cn.afterturn.easypoi.excel.annotation.ExcelIgnore;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang3.time.DateUtils;

import java.time.LocalDateTime;

/**
 * @Classname 医院月度报表对象
 * @Description TODO
 * @Date 2020/3/30 4:19 下午
 * @Created yangyang.jiang
 */
@Data
@ExcelTarget(value = "月度报表")
@ApiModel(value = "月度报表对象", description = "月度报表对象")
public class HospitalMonthVo extends BaseVo {

    @ApiModelProperty(value = "月度报表id")
    private Long id;

    @ApiModelProperty(value = "医院名称")
    @Excel(name = "医院名称", width = 40)
    private String hospitalName;

    @Excel(name = "月份", width = 10)
    @ApiModelProperty(value = "月份")
    private String month;

    @Excel(name = "上报时间", width = 20)
    @ApiModelProperty(value = "上报时间")
    private String time;

    @Excel(name = "上报人名称", width = 30)
    @ApiModelProperty(value = "上报人名称")
    private String name;

    @Excel(name = "手机号", width = 20)
    @ApiModelProperty(value = "手机号")
    private String mobile;

    @Excel(name = "所属科室", width = 20)
    @ApiModelProperty(value = "所属科室")
    private String dept;

    @Excel(name = "职称", width = 20)
    @ApiModelProperty(value = "职称")
    private String post;

    @ApiModelProperty(value = "上报状态")
    private Integer state;

    @ApiModelProperty(value = "上报状态", hidden = true)
    @Excel(name = "上报状态", width = 20)
    private String stateName;
}
