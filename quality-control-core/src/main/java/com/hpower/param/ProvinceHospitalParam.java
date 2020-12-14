package com.hpower.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Classname 全省医院分布入参对象
 * @Description TODO
 * @Date 2020/4/2 8:43 上午
 * @Created yangyang.jiang
 */
@Data
@ApiModel(value = "全省医院分布入参对象",description = "全省医院分布入参对象")
public class ProvinceHospitalParam extends BaseParam{

    @ApiModelProperty(value = "城市id")
    private Long cityId;

    @ApiModelProperty(value = "区域id")
    private Long areaId;
}
