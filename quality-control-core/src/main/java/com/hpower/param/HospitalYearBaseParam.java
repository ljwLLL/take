package com.hpower.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.Valid;

/**
 * @Classname 医院年报基础
 * @Description TODO
 * @Date 2020/4/1 11:06 上午
 * @Created yangyang.jiang
 */
@Data
@ApiModel(value = "医院年报基础类",description = "医院年报基础类")
public class HospitalYearBaseParam extends BaseParam{

    @ApiModelProperty(value = "医院级别 1三级 2二级 3一级 4其他")
    private String level;

    @ApiModelProperty(value = "年度住院患者数")
    private String yearPatientCount;

    @ApiModelProperty(value = "医院编制床位数")
    private String bedCount;

    @ApiModelProperty(value = "医院是否开设营养科")
    private Integer whetherNutrition;

    @ApiModelProperty(value = "营养科归属部门 1临床 2医技 3后勤 4其他")
    private Integer nutritionDept;

    @ApiModelProperty(value = "营养科归属部门名称(其他)")
    private String nutritionManagerDept;

    @ApiModelProperty(value = "是否每日营养科查房")
    private Integer whetherDayRound;

    @ApiModelProperty(value = "营养科是否有三级查房制度")
    private Integer whetherThreeRound;

    @ApiModelProperty(value = "营养查房工作开展 1营养医师 2营养技师 3营养护士 4其他")
    private String typeNumber;

    @ApiModelProperty(value = "住院患者营养风险筛查总数")
    private String patientScreenCount;

    @ApiModelProperty(value = "住院患者营养风险筛查阳性总数")
    private String patientScreenPositiveCount;

    @ApiModelProperty(value = "营养科是否有独立收费项目")
    private Integer whetherIndependent;

    @ApiModelProperty(value = "营养科共有几项独立收费项目")
    private String whetherIndependentCount;

    @ApiModelProperty(value = "营养科营养教学科研 1承担高校营养教学任务 2承担进修带教 3开展本院医务人员营养专业培养 4开展本院患者营养宣教 5举办继续医学教育学习班 6开展临床营养科研项目")
    private String nutritionResearch;
}
