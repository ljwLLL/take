package com.hpower.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Classname 全省医院分布返回对象
 * @Description TODO
 * @Date 2020/4/2 8:46 上午
 * @Created yangyang.jiang
 */
@Data
@ApiModel(value = "全省医院分布返回对象",description = "全省医院分布返回对象")
public class ProvinceHospitalVo extends BaseVo{

    @ApiModelProperty(value = "医院类型")
    private String type;

    @ApiModelProperty(value = "医院数量")
    private Integer hospitalCount;

    @ApiModelProperty(value = "医院占比")
    private String percentage;
}
