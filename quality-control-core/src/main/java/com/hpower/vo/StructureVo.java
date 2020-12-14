package com.hpower.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Classname 结构指标对象
 * @Description TODO
 * @Date 2020/3/31 9:46 上午
 * @Created yangyang.jiang
 */
@Data
@ApiModel(value = "结构指标对象",description = "结构指标对象")
public class StructureVo extends BaseVo{

    @ApiModelProperty(value = "NUT-S-01医室数")
    private String nutsOneNumber;

    @ApiModelProperty(value = "NUT-S-01床位数")
    private String nutsOneCount;

    @ApiModelProperty(value = "NUT-S-01医床比")
    private String nutsOnePerentage;

    @ApiModelProperty(value = "NUT-S-02医室数")
    private String nutsTowNumber;

    @ApiModelProperty(value = "NUT-S-02床位数")
    private String nutsTowCount;

    @ApiModelProperty(value = "NUT-S-02医床比")
    private String nutsTowPerentage;

    @ApiModelProperty(value = "NUT-S-03医室数")
    private String nutsThreeNumber;

    @ApiModelProperty(value = "NUT-S-03床位数")
    private String nutsThreeCount;

    @ApiModelProperty(value = "NUT-S-03医床比")
    private String nutsThreePerentage;
}
