package com.hpower.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @Classname 月度报表查询对象
 * @Description TODO
 * @Date 2020/3/30 4:24 下午
 * @Created yangyang.jiang
 */
@Data
@ApiModel(value = "月度报表查询对象", description = "月度报表查询对象")
public class HospitalMonthParam extends BasePageParam {

    @ApiModelProperty(value = "数据类型所有", hidden = true)
    public static final String TYPE_ALL = "0";

    @ApiModelProperty(value = "数据类型部分", hidden = true)
    public static final String TYPE_ONLY = "1";

    @ApiModelProperty(value = "医院id",example = "1")
    private Long hospitalId;

    @ApiModelProperty(value = "开始月份")
    private String startMonth;

    @ApiModelProperty(value = "结束月份")
    private String endMonth;

    @ApiModelProperty(value = "开始上报时间")
    private String startTime;

    @ApiModelProperty(value = "结束上报时间")
    private String endTime;

    @ApiModelProperty(value = "医院id串", hidden = true)
    private List<Long> list;

    @ApiModelProperty(value = "数据类型", hidden = true)
    private String type;

    @ApiModelProperty(value = "质控中心id", hidden = true,example = "1")
    private Long qualityId;

    @ApiModelProperty(value = "报表上传状态")
    private Integer state;
}
