package com.hpower.vo;

import com.hpower.param.HospitalYearBaseParam;
import com.hpower.param.HospitalYearFunctionParam;
import com.hpower.param.HospitalYearUserParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "年度统计对象,对应单个医院", description = "年度统计对象,对应单个医院")
public class AnalyseYearLookOneVO extends BaseVo {

    /**
     * 年度指标id
     */
    @ApiModelProperty(value = "年度指标id")
    private Long yearId;

    /**
     * 上报人姓名
     */
    @ApiModelProperty(value = "上报人姓名")
    private String name;

    /**
     * 部门
     */
    @ApiModelProperty(value = "部门")
    private String dept;

    /**
     * 职务
     */
    @ApiModelProperty(value = "职务")
    private String post;

    /**
     * 手机号码
     */
    @ApiModelProperty(value = "手机号码")
    private String mobile;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;


    @ApiModelProperty(value = "年度报表基础区")
    private HospitalYearBaseParam hospitalYearBaseParam;

    @ApiModelProperty(value = "年度报表功能区")
    private HospitalYearFunctionParam hospitalYearFunctionParam;

    @ApiModelProperty(value = "年度报表用户区")
    private HospitalYearUserParam hospitalYearUserParam;



}