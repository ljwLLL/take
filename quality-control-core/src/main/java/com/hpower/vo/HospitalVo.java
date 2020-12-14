package com.hpower.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Classname HospitalVo
 * @Description TODO
 * @Date 2020/3/30 10:47 上午
 * @Created yangyang.jiang
 */
@Data
@ApiModel(value = "医院查询对象返回信息", description = "医院查询对象返回信息")
public class HospitalVo extends BaseVo {

    @ApiModelProperty(value = "医院id")
    private Long id;

    @ApiModelProperty(value = "医院名称")
    private String hospitalName;

    @ApiModelProperty(value = "医院编码")
    private String hospitalCode;

    @ApiModelProperty(value = "医院级别")
    private Integer level;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "启用状态")
    private Integer enabled;
}
