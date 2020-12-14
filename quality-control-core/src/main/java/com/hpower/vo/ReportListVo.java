package com.hpower.vo;

import com.hpower.param.BaseParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @Classname 全省最新上班信息
 * @Description TODO
 * @Date 2020/4/2 9:26 上午
 * @Created yangyang.jiang
 */
@Data
@ApiModel(value = "全省最新上报信息", description = "全省最新上报信息")
public class ReportListVo extends BaseParam {

    @ApiModelProperty(value = "月度上报情况")
    private List<ReportVo> monthReport;

    private String month;

    @ApiModelProperty(value = "年度上报情况")
    private List<ReportVo> yearReport;

    private String year;

    @Data
    @ApiModel(value = "上报信息")
    public static class ReportVo extends BaseVo {
        private String type;

        private String percentage;

        private Integer reportCount;
    }
}
