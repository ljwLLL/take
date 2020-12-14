package com.hpower.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Classname 根据医院id查询月度报表信息
 * @Description TODO
 * @Date 2020/4/6 8:24 下午
 * @Created yangyang.jiang
 */
@Data
@ApiModel(value = "根据医院id查询月度报表信息vo", description = "根据医院id查询月度报表信息")
public class AnalyseMonthDetailVo extends BaseVo {

    @ApiModelProperty(value = "月度id")
    private Long id;

    @ApiModelProperty(value = "医院名称")
    private String hospitalName;

    @ApiModelProperty(value = "月份")
    private String month;

    @ApiModelProperty(value = "上报时间")
    private LocalDateTime time;

    @ApiModelProperty(value = "上报人")
    private String name;

    @ApiModelProperty(value = "手机号码")
    private String mobile;

    @ApiModelProperty(value = "所属科室")
    private String dept;

    @ApiModelProperty(value = "职称")
    private String post;

    @ApiModelProperty(value = "上报状态")
    private Integer state;
}
