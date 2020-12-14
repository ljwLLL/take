package com.hpower.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Classname 月度报表分析
 * @Description TODO
 * @Date 2020/4/5 1:49 下午
 * @Created yangyang.jiang
 */
@Data
@ApiModel(value = "月度报表分析",description = "月度报表分析")
public class AnalyseMonthVo extends BaseVo{

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

    @ApiModelProperty(value = "如果有月度报表的信息，那就返回他的第一条id，就是月份报表的id")
    @JsonProperty("monthId")
    private Long id;

    @ApiModelProperty(value = "月份")
    private String month;

    private Integer state;
}
