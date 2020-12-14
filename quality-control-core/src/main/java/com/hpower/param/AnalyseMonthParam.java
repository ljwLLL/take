package com.hpower.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @Classname 分页查询月度报表统计对象
 * @Description TODO
 * @Date 2020/4/5 1:58 下午
 * @Created yangyang.jiang
 */
@Data
@ApiModel(value = "分页查询月度报表统计对象", description = "分页查询月度报表统计对象")
public class AnalyseMonthParam extends BasePageParam {

    @ApiModelProperty(value = "医院id")
    private Long hospitalId;

    @ApiModelProperty(value = "医院id串", hidden = true)
    private List<Long> list;

    @ApiModelProperty(value = "数据类型", hidden = true)
    private String type;

    @ApiModelProperty(value = "质控中心id",hidden = true)
    private Long qualityId;

    @ApiModelProperty(value = "数据类型所有", hidden = true)
    public static final String TYPE_ALL = "0";

    @ApiModelProperty(value = "数据类型部分", hidden = true)
    public static final String TYPE_ONLY = "1";
}
