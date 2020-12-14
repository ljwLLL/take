package com.hpower.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Classname 年份报表基础信息
 * @Description TODO
 * @Date 2020/4/1 3:54 下午
 * @Created yangyang.jiang
 */
@Data
@ApiModel(value = "年份报表基础信息",description = "年份报表基础信息")
public class YearBaseVo extends BaseVo{

    @ApiModelProperty(value = "医院级别")
    private String level;

    @ApiModelProperty(value = "年度住院患者数")
    private String yearPatientCount;

    @ApiModelProperty(value = "医院编制床位数")
    private String bedCount;

    @ApiModelProperty(value = "医院是否开设营养科")
    private Integer whetherNutrition;

    @ApiModelProperty(value = "营养科归属部门")
    private Integer nutritionDept;

    @ApiModelProperty(value = "营养科归属部门名称(其他)")
    private String nutritionManagerDept;

    @ApiModelProperty(value = "是否每日营养科查房")
    private Integer whetherDayRound;

    @ApiModelProperty(value = "营养科是否有三级查房制度")
    private Integer whetherThreeRound;

    @ApiModelProperty(value = "营养查房工作开展")
    private String typeNumber;

    @ApiModelProperty(value = "住院患者营养风险筛查总数")
    private String patientScreenCount;

    @ApiModelProperty(value = "住院患者营养风险筛查阳性总数")
    private String patientScreenPositiveCount;

    @ApiModelProperty(value = "营养科是否有独立收费项目")
    private Integer whetherIndependent;

    @ApiModelProperty(value = "营养科共有几项独立收费项目")
    private String whetherIndependentCount;

    @ApiModelProperty(value = "营养科营养教学科研")
    private String nutritionResearch;
}
