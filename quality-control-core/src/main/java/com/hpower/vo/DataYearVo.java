package com.hpower.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @Classname 总览年度报表
 * @Description TODO
 * @Date 2020/4/3 9:52 上午
 * @Created yangyang.jiang
 */
@Data
@ApiModel(value = "总览年度报表", description = "总览年度报表")
public class DataYearVo extends BaseVo {

    @ApiModelProperty(value = "营养科专业人员和床位比分布统计图(1:比值≥0.5 2:比值＜0.5 3无法统计)")
    private List<DataYearCommonVo> bedList;

    @ApiModelProperty(value = "不同等级医院中住院病人营养风险筛查率的分布统计图(1:比值≥0.5 2:比值＜0.5 3无法统计)")
    private List<DataYearCommonVo> patientList;

    @ApiModelProperty(value = "不同等级医院中有肠内营养配制室的比例排行(0无 1有)")
    private List<DataYearCommonVo> entericList;

    @ApiModelProperty(value = "不同等级医院营养科是否有独立收费项目分布排行(0无 1有)")
    private List<DataYearCommonVo> ownList;
}
