package com.hpower.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @Classname HospitalParam
 * @Description TODO
 * @Date 2020/3/30 10:46 上午
 * @Created yangyang.jiang
 */
@Data
@ApiModel(value = "医院查询入参对象", description = "医院查询入参对象")
public class HospitalParam extends BasePageParam {

    @ApiModelProperty(value = "医院名称")
    private String hospitalName;

    @ApiModelProperty(value = "数据类型" ,hidden = true)
    private String type;

    @ApiModelProperty(value = "医院id串" , hidden = true)
    private List<Long> list;

    @ApiModelProperty(value = "全部医院数据类型" , hidden = true)
    public static final String TYPE_ALL = "0";

    @ApiModelProperty(value = "部分医院数据类型" , hidden = true)
    public static final String TYPE_ONLY = "1";
}
