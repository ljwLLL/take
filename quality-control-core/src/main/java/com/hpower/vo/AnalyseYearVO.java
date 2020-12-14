package com.hpower.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AnalyseYearVO  extends BaseVo {

    @ApiModelProperty(value = "医院id")
    private Long hospitalId;

    @ApiModelProperty(value = "医院名称")
    private String hospitalName;

    @ApiModelProperty(value = "未上报数量")
    private Integer noReport;

    @ApiModelProperty(value = "已上报数量")
    private Integer yesReport;

    @ApiModelProperty(value = "最近上报时间")
    private LocalDateTime time;

    @ApiModelProperty(value = "上报人")
    private String name;

    @ApiModelProperty(value = "手机号码")
    private String mobile;

    @ApiModelProperty(value = "所属科室")
    private String dept;

    @ApiModelProperty(value = "职称")
    private String post;
}
