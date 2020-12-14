package com.hpower.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @Classname 住院患者营养风险筛查率
 * @Description TODO
 * @Date 2020/4/2 1:30 下午
 * @Created yangyang.jiang
 */
@Data
@ApiModel(value = "住院患者营养风险筛查率", description = "住院患者营养风险筛查率")
public class PatientParam extends BaseParam {

    @ApiModelProperty(value = "入参类型0近一个月 1近半年 2近一年 3时间筛查 4无条件")
    @NotNull(message = "查询类型不能为空")
    private Integer type;

    @ApiModelProperty(value = "开始月份")
    private String startMonth;

    @ApiModelProperty(value = "结束月份")
    private String endMonth;

    @ApiModelProperty(value = "标签入参集合")
    @Valid
    @NotNull(message = "标签入参集合不能为空")
    private List<TagParam> list;

    @Data
    @ApiModel(value = "标签入参对象", description = "标签入参对象")
    public static class TagParam {

        @ApiModelProperty(value = "月度指标标签编码,后台字段名称")
        @NotEmpty(message = "标签编码不能为空")
        private String tag;

        @ApiModelProperty(value = "月度指标标签名称")
        @NotEmpty(message = "标签名称不能为空")
        private String tagName;
    }
}
