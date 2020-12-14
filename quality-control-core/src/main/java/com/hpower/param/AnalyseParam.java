package com.hpower.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @Classname 统计分析
 * @Description TODO
 * @Date 2020/3/30  下午
 * @Created jianwen.liu
 */
@Data
@ApiModel(value = "统计分析可月度可查看对象", description = "统计分析可月度可查看对象")
public class AnalyseParam extends BasePageParam {

    /**
     * 医院名称
     */
    @ApiModelProperty(value = "医院id")
    private Long hospitalId;

    @ApiModelProperty(value = "质控中心id", hidden = true)
    private Long qualityId;

    @ApiModelProperty(value = "数据类型", hidden = true)
    private String type;

    @ApiModelProperty(value = "数据类型所有", hidden = true)
    public static final String TYPE_ALL = "0";

    @ApiModelProperty(value = "数据类型部分", hidden = true)
    public static final String TYPE_ONLY = "1";

    @ApiModelProperty(value = "医院id串", hidden = true)
    private List<Long> list;

}
