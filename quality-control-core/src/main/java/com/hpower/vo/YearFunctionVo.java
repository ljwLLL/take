package com.hpower.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Classname 年份报表功能信息
 * @Description TODO
 * @Date 2020/4/1 3:55 下午
 * @Created yangyang.jiang
 */
@Data
@ApiModel(value = "年份报表功能信息",description = "年份报表功能信息")
public class YearFunctionVo extends BaseVo{

    @ApiModelProperty(value = "营养科功能区")
    private String nutritionFunction;

    @ApiModelProperty(value = "营养科门诊是否有独立诊疗号")
    private Integer whetherDiagnosticNumber;

    @ApiModelProperty(value = "营养门诊投诉例数")
    private String complaintCount;

    @ApiModelProperty(value = "营养科代谢室归属")
    private Integer nutritionDept;

    @ApiModelProperty(value = "营养科代谢室归属单位(其他)")
    private String nutritionDeptElse;

    @ApiModelProperty(value = "营养代谢室面积")
    private String nutritionArea;

    @ApiModelProperty(value = "代谢检测工作开展")
    private String metabolizeWorkNumber;

    @ApiModelProperty(value = "营养科对住院患者能量代谢测定例数")
    private String hosEnergyNumber;

    @ApiModelProperty(value = "营养科对住院患者成分测定例数")
    private String hosComponentNumber;

    @ApiModelProperty(value = "营养科门诊患者能量代谢测定例数")
    private String depEnergyNumber;

    @ApiModelProperty(value = "营养科门诊患者体成分测定例数")
    private String depComponentNumber;

    @ApiModelProperty(value = "肠外营养配制室归属")
    private Integer parenteralDept;

    @ApiModelProperty(value = "肠外营养配置室归属单位(其他)")
    private String parenteralDeptElse;

    @ApiModelProperty(value = "肠外营养配置室面积")
    private String parenteralArea;

    @ApiModelProperty(value = "肠外营养配置工作开展")
    private String parenteralWorkNumber;

    @ApiModelProperty(value = "营养科肠外营养治疗患者总例数")
    private String parenteralPatientNumber;

    @ApiModelProperty(value = "肠外营养治疗不良事件发生例数")
    private String parenteralBadNumber;

    @ApiModelProperty(value = "肠内营养配置室归属")
    private Integer entericeDept;

    @ApiModelProperty(value = "肠内营养配置室归属(其他)")
    private String entericeDeptElse;

    @ApiModelProperty(value = "肠内营养配制室面积")
    private String entericeArea;

    @ApiModelProperty(value = "肠内营养配置工作开展")
    private String entericeWorkNumber;

    @ApiModelProperty(value = "营养科肠内营养治疗患者总例数")
    private String entericePatientNumber;

    @ApiModelProperty(value = "肠内营养治疗不良事件发生数")
    private String entericeBadNumber;

    @ApiModelProperty(value = "医疗膳食配制室归属")
    private Integer dietDept;

    @ApiModelProperty(value = "医疗膳食配置室面积")
    private String dietArea;

    @ApiModelProperty(value = "营养烹饪技师总人数")
    private String nutritionCookerNumber;

    @ApiModelProperty(value = "营养膳食护士总人数")
    private String nutritionNurseNumber;

    @ApiModelProperty(value = "全院基本膳食医嘱总例数")
    private String dietWillNumber;

    @ApiModelProperty(value = "全院营养素调整膳食总例数")
    private String dietNumber;

    @ApiModelProperty(value = "全院糖尿病膳食总例数")
    private String diabetesNumber;

    @ApiModelProperty(value = "全院限制蛋白膳食总例数")
    private String proteinNumber;

    @ApiModelProperty(value = "全院低嘌呤膳食总例数")
    private String lowPurineNumber;

    @ApiModelProperty(value = "医疗膳食治疗不良事件发生例数")
    private String treatBedNumber;

    @ApiModelProperty(value = "营养科专科病房床位数")
    private String specialtyBedNumber;

    @ApiModelProperty(value = "住院病例数")
    private String patientNumber;
}
