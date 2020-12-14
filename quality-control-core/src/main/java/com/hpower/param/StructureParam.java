package com.hpower.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @Classname 医院月度报表结构指标
 * @Description TODO
 * @Date 2020/3/30 5:21 下午
 * @Created yangyang.jiang
 */
@Data
@ApiModel(value = "医院月度报表结构指标", description = "医院月度报表结构指标")
public class StructureParam extends BaseParam {

    @ApiModelProperty(value = "NUT-S-01医室数")
    @NotEmpty(message = "NUT-S-01医室数不能为空")
    private String nutsOneNumber;

    @ApiModelProperty(value = "NUT-S-01床位数")
    @NotEmpty(message = "NUT-S-01床位数不能为空")
    private String nutsOneCount;

    @ApiModelProperty(value = "NUT-S-01医床比")
    @NotEmpty(message = "NUT-S-01医床比")
    private String nutsOnePerentage;

    @ApiModelProperty(value = "NUT-S-02医室数")
    @NotEmpty(message = "NUT-S-02医室数不能为空")
    private String nutsTowNumber;

    @ApiModelProperty(value = "NUT-S-02床位数")
    @NotEmpty(message = "NUT-S-02床位数不能为空")
    private String nutsTowCount;

    @ApiModelProperty(value = "NUT-S-02医床比")
    @NotEmpty(message = "NUT-S-02医床比")
    private String nutsTowPerentage;

    @ApiModelProperty(value = "NUT-S-03医室数")
    @NotEmpty(message = "NUT-S-03医室数不能为空")
    private String nutsThreeNumber;

    @ApiModelProperty(value = "NUT-S-03床位数")
    @NotEmpty(message = "NUT-S-03床位数不能为空")
    private String nutsThreeCount;

    @ApiModelProperty(value = "NUT-S-03医床比")
    @NotEmpty(message = "NUT-S-03医床比")
    private String nutsThreePerentage;
}
