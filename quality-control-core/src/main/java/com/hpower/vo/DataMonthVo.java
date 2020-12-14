package com.hpower.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Classname 总览月度报表信息
 * @Description TODO
 * @Date 2020/4/2 12:25 下午
 * @Created yangyang.jiang
 */
@Data
@ApiModel(value = "总览月度报表", description = "总览月度报表")
public class DataMonthVo extends BaseVo {

    @ApiModelProperty(value = "标签名称")
    private String tagName;

    @ApiModelProperty(value = "标签编码")
    private String tag;

    @ApiModelProperty(value = "单个指标明细信息")
    private List<DataMonthDetailVo> list;
}
