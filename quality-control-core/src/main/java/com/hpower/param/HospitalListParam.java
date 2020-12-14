package com.hpower.param;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @Classname 医院新增对象
 * @Description TODO
 * @Date 2020/3/30 10:59 上午
 * @Created yangyang.jiang
 */
@Data
@ApiModel(value = "医院集合查询对象", description = "医院集合查询对象")
public class HospitalListParam extends BaseParam {

    @ApiModelProperty(value = "查询类型 1表示所有医院信息")
    @NotNull(message = "查询类型不能为空")
    private Integer type = 0;
}
