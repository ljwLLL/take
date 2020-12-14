package com.hpower.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author LENOVO
 * @Classname 查询浙江省医院的记录
 * @Description TODO
 * @Date 2020/06/23
 * @Created jianwen.liu
 */
@Data
@ApiModel(value = "查询浙江省所有医院的历史记录" ,description = "查询浙江省所有医院的历史记录,按月份分")
public class AnalyseMonthHistoryVO {
    @ApiModelProperty(value = "医院名称")
    private String historyName;
    @ApiModelProperty(value = "月份")
    private String month;
    @ApiModelProperty(value = "已上报状态")
    private Integer yesReport;
    @ApiModelProperty(value = "未上报状态")
    private Integer noReport;
    @ApiModelProperty(value = "未上报状态的医院")
    private List<String> noReportHistory;
}
