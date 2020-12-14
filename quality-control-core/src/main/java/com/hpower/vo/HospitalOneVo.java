package com.hpower.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Classname 医院返回对象
 * @Description TODO
 * @Date 2020/3/30 11:22 上午
 * @Created yangyang.jiang
 */
@Data
@ApiModel(value = "医院返回对象", description = "医院返回对象")
public class HospitalOneVo extends BaseVo {

    @ApiModelProperty(value = "医院id")
    private Long id;

    @ApiModelProperty(value = "医院编码")
    private String hospitalCode;

    @ApiModelProperty(value = "医院名称")
    private String hospitalName;

    @ApiModelProperty(value = "医院级别")
    private Integer level;

    @ApiModelProperty(value = "省份编码")
    private Long provinceCode;

    @ApiModelProperty(value = "城市编码")
    private Long cityCode;

    @ApiModelProperty(value = "区域编码")
    private Long areaCode;
}
